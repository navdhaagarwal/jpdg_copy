package jpdg_copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import graph.Graph;
import label.LabelMaker;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.toolkits.graph.Block;
import soot.toolkits.graph.BlockGraph;
import soot.toolkits.graph.UnitBlockGraph;
import soot.util.Chain;

public class PDG_Builder {
	LabelMaker lm;
	Graph g = new Graph();
	CallGraph cg;
	Chain<soot.SootClass> classes;
	List<String> excluded;

    public static Graph build(CallGraph cg, LabelMaker lm, Chain<soot.SootClass> classes, List<String> excluded) {
        PDG_Builder self = new PDG_Builder(cg, lm, classes, excluded);
        self.build_PDG();
        return self.g;
    }
    
    private PDG_Builder(CallGraph cg, LabelMaker lm, Chain<soot.SootClass> classes, List<String> excluded) {
        this.cg = cg;
        this.lm = lm;
        this.classes = classes;
        this.excluded = excluded;
    }
    
    void build_PDG() {
        System.out.println("Classes "+ classes);
        List<soot.SootClass> allowed = new ArrayList<soot.SootClass>();
        for (soot.SootClass c : classes) {
            String pkg_name = c.getPackageName();
            String cls_name = c.getName();
            boolean use = true;
            for (String exclude : excluded) {
                if (exclude.startsWith("*") && exclude.endsWith("*")) {
                    exclude = exclude.substring(1, exclude.length()-1);
                    if (pkg_name.contains(exclude) || cls_name.contains(exclude)) {
                        use = false;
                        break;
                    }
                } else if (exclude.startsWith("*")) {
                    exclude = exclude.substring(1);
                    if (pkg_name.endsWith(exclude) || cls_name.contains(exclude)) {
                        use = false;
                        break;
                    }
                } else {
                    if (pkg_name.startsWith(exclude)) {
                        use = false;
                        break;
                    }
                }
            }
            if (use) {
                allowed.add(c);
            }
        }
        Map<String,Integer> method_entries = new HashMap<String,Integer>();
        for (soot.SootClass c : allowed) {
            String source = "";
            try {
                source = new String(c.getTag("SourceFileTag").getValue(), "UTF-8");
            } catch (java.io.UnsupportedEncodingException e) {
                throw new RuntimeException(e.toString());
            } catch (java.lang.Exception e) {
                source = "unknown";
            }
            List<soot.SootMethod> methods = null;
            try {
              methods = c.getMethods();
            } catch (java.lang.Exception e) {
              continue;
            }
            for (soot.SootMethod m : methods) {
                String name = pDG_Build.method_name(m);
                int entry_uid = g.addNode(
                    name, "",
                    c.getPackageName(), c.getName(), source, m.getSignature(),
                    "entry",
                    m.getJavaSourceStartLineNumber(),
                    m.getJavaSourceStartColumnNumber(),
                    m.getJavaSourceStartLineNumber(),
                    m.getJavaSourceStartColumnNumber()
                );
                method_entries.put(name, entry_uid);
            }
        }
        for (soot.SootClass c : allowed) {
            try {
                process_class(method_entries, c);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    void process_class(Map<String,Integer> method_entries, soot.SootClass c) throws pDG_Build.Error {
        System.out.println(c);
        for (soot.SootMethod m : c.getMethods()) {
            System.out.println(String.format("   %s", m));
            soot.Body body = null;
            try {
                body = m.retrieveActiveBody();
            } catch (RuntimeException e) {
                System.err.println(e);
                continue;
            }
            BlockGraph ebg = new UnitBlockGraph(body);
            try {
                pDG_Build.build(cg, method_entries, lm, g, c, m, body, ebg);
            } catch (pDG_Build.SootError e) {
                System.err.println(String.format("soot error for method %s\n%s", m, e));
                continue;
            }
        }
    }
}
