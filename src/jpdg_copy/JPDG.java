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

import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;

public class JPDG {
	public static void main(String args[])
	{
		List<String> dirs = new ArrayList<String>();
		List<String> excluded = new ArrayList<String>();
        String output = "output";
        String label_type = "op";
        dirs.add("test");
        
		soot.G.reset();
		addPacks();
		soot.Scene S = soot.Scene.v();
        Options O = Options.v();
        
        O.set_process_dir(dirs);
        O.set_output_format(Options.output_format_jimple);
        O.set_keep_line_number(true);
        O.set_allow_phantom_refs(true);
        O.set_keep_offset(true);
        O.set_verbose(false);
        
        S.loadNecessaryClasses();
        soot.PackManager.v().runPacks();
		
        writeGraph(build_PDG(S, excluded, label_type),output);
        
	}
	
	
	public static void addPacks() {
		soot.PackManager.v().getPack("wjtp").add(new soot.Transform("wjtp.myTrans", new soot.SceneTransformer() {
            @Override
            protected void internalTransform(String phaseName, Map options) {
                CHATransformer.v().transform();
                CallGraph cg = soot.Scene.v().getCallGraph();
            }
        }));
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
        if (false) {
            cg = S.getCallGraph();
        }
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
