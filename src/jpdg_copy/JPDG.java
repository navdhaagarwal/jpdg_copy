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
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.Transform;
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
        dirs.add("C:/Users/navdh/Desktop/project/java_file");
        
		soot.G.reset();
		
		Scene S = Scene.v();
		Options O = Options.v();
		S.setSootClassPath(Scene.v().getSootClassPath());
		S.extendSootClassPath(System.getProperty("user.dir")+System.getProperty("file.separator")+"bin");
		O.set_process_dir(dirs);
        O.set_output_format(Options.output_format_jimple);
        O.set_keep_line_number(true);
        O.set_allow_phantom_refs(true);
        O.set_keep_offset(true);
        O.set_verbose(false);
        O.set_whole_program(true);
        O.set_no_bodies_for_excluded(true);
        O.setPhaseOption("cg", "safe-newinstance");
//        O.setPhaseOption("jb", "use-original-names:true");
        SootClass c = Scene.v().loadClassAndSupport(args[0]);//set class to test suite to analyze
        c.setApplicationClass();
        S.loadNecessaryClasses();
        soot.Main.main(args);
        
        soot.PackManager.v().runPacks();
        writeGraph(build_PDG(S, excluded, label_type),output);
        
	}
	
	
	public static void addPacks() {
		PackManager.v().getPack("wjtp").add( new Transform("wjtp.myTransform", new SceneTransformer() {
			        protected void internalTransform(String phaseName,
			            Map options) {
			          System.err.println(Scene.v().getApplicationClasses());
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
