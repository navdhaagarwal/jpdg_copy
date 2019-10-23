package jpdg_copy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graph.Graph;
import label.LabelMaker;
import label.ExpressionTreeLabels;
import label.InstructionLabels;
import label.OpLabels;
import soot.Body;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;

public class JPDG {
	public static void main(String args[])
	{
		List<String> dirs = new ArrayList<String>();
		List<String> excluded = new ArrayList<String>();
        String output = "./output/output";
        String label_type = "op";
        dirs.add("C:/Users/navdh/Desktop/project/java_file");

//        writeGraph(build_PDG(S, excluded, label_type),output);
      
		
		Scene.v().setSootClassPath(Scene.v().getSootClassPath());
		Scene.v().extendSootClassPath(System.getProperty("user.dir")+"\\bin");
		Options.v().set_output_format(Options.output_format_jimple);
		Options.v().set_keep_line_number(true);
		Options.v().set_allow_phantom_refs(true);
		Options.v().set_keep_offset(true);
		Options.v().set_ignore_resolution_errors(true);
		Options.v().set_verbose(false);
		Options.v().setPhaseOption("jb", "use-original-names:true");
		PackManager.v().getPack("jtp").add(new Transform("jtp.instrumenter", new MyAnalysis() {
			protected void internalTransform(Body body, String phase, Map options) { 
				writeGraph(build_PDG(Scene.v(), excluded, label_type),output);
			}
				
		}));
		String a[] = new String[1];
		a[0] = "jpdg_copy.Add";
		
		soot.Main.main(a);
		
		
	}
	
	public static Graph build_PDG(soot.Scene S, List<String> excluded ,String label_type) {
		LabelMaker lm = null;
        if (label_type.equals("inst")) {
            lm = new InstructionLabels();
        } else if (label_type.equals("expr-tree")) {
            lm = new ExpressionTreeLabels();
        } else if (label_type.equals("op")) {
            lm = new OpLabels();
        } else {
            throw new RuntimeException("uknown label type: " + label_type);
        }
        System.out.println("LABEL TYPE " + label_type + " " + lm);
        soot.util.Chain<soot.SootClass> classes = S.getApplicationClasses();
        CallGraph cg = null;
//        if (false) {
//            cg = S.getCallGraph();
//        }
        return PDG_Builder.build(cg, lm, classes, excluded);
	}
	
	public static void writeGraph(Graph g, String path) {
        FileOutputStream s = null;
        try {
            s = new FileOutputStream(path);
            g.Write(s);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
           try {s.close();} catch (Exception ex) {}
        }
    }
}
