package jpdg_copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.G;
import soot.Local;
import soot.PackManager;
import soot.PatchingChain;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.IfStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.toolkits.annotation.logic.Loop;
import soot.jimple.toolkits.annotation.logic.LoopFinder;
import soot.options.Options;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.util.Chain;

/**
 *
 * @author Devika
 *
 */
public class MethodAnalysis {

public static void main(String[] args) {
	
	
	//String p=Scene.v().getSootClassPath()+";"+"/C:/Users/Devika/Desktop/workspace/SootPrac/bin/trans/Foo.class";
	//G.v().out.println(p);
	//Scene.v().setSootClassPath(p);
	
	//Options.v().set_soot_classpath(Scene.v().defaultClassPath() + p);
	/*SootClass c = Scene.v().loadClassAndSupport(p);
	        c.setApplicationClass();
	G.v().out.println("Class Fields"+c.getFields()+"\n");
	*/
	List<String> dirs = new ArrayList<String>();
	dirs.add("C:\\Users\\navdh\\eclipse-workspace\\jpdg_copy\\bin\\jpdg_copy");
    
	System.out.println(Scene.v().getSootClassPath());
	Scene.v().extendSootClassPath(System.getProperty("user.dir")+"\\bin");
	Options.v().set_output_format(Options.output_format_jimple);
	Options.v().set_keep_line_number(true);
	Options.v().setPhaseOption("jb", "use-original-names:true");
	Options.v().set_process_dir(dirs);
	System.out.println(Scene.v().getSootClassPath());
//	Scene.v().loadNecessaryClasses();
	System.out.println(Scene.v().getClasses());
	PackManager.v().getPack("jtp").add(new Transform("jtp.instrumenter", new MyAnalysis() ));
	Options.v().set_output_format(Options.output_format_jimple);
//	soot.Main.main(args);
	}
}

class MyAnalysis extends BodyTransformer {


	protected void internalTransform(Body body, String phase, Map options) {
	
		G.v().out.println("Method Name: "+body.getMethod().getName());
		G.v().out.println("Method Return Type: "+body.getMethod().getReturnType());
		G.v().out.println("Method Parameter Type: "+body.getMethod().getParameterTypes());
		G.v().out.println("Method Local Variables with Type(excluding temp variables): "+getlocalwithtype(body));
		
		G.v().out.println("Exceptions thrown by Methods: "+body.getMethod().getExceptions());
		G.v().out.println("Method Invocations: "+getmethodinvocation(body));
		//PatchingChain<Unit> loopcheck = body.getMethod().getActiveBody().getUnits();
	//	if(body.getMethod().getParameterCount()>0)
	//	G.v().out.println("Method Implicit Parameter: "+body.getLocals());
	//	LoopFinder loopFinder = new LoopFinder();
	//	   loopFinder.transform(body);
	//	   SootMethod sm=body.getMethod();
	//	   System.out.println(sm.getSubSignature());
	//	   Collection<Loop> loops = loopFinder.loops();
	//	   for(Loop l: loops)
	//	   {
	//	    G.v().out.println("Contains loop with loop body: "+l.getLoopStatements());
	//	    if(l.getLoopStatements().get(1) instanceof IfStmt)
	//	    {
	//	    IfStmt s=(IfStmt)l.getLoopStatements().get(1);
	//	    System.out.println(s.getCondition());
	//	    }
	//	   }
	//	/*if(loopcheck.toString().contains("goto "))
	//	{
	//	G.v().out.println("Contains jump: "+loopcheck.toString());
	//	}*/
	//	List<ValueBox> usebox=body.getUseBoxes();
	//	for(ValueBox val: usebox)
	//	{
	//	if(val.getValue().toString().contains("*"))
	//	G.v().out.println("'*' operator present at "+val.getValue());
	//	
	//	
	//	}

		G.v().out.println("\n*******************************\n");
	}
	public MyAnalysis() {
		//doAnalysis();
	}
	
	public HashMap<String,String> getmethodinvocation(Body body)
	{
		String[] str=new String[2];
		HashMap<String,String> hm=new HashMap<String,String>();
		List<ValueBox> usebox=body.getUseBoxes();
		String val_str="";
		for(ValueBox val: usebox)
		{
			val_str=val.getValue().toString();
			System.out.println("value "+val_str+", "+val.getValue().getUseBoxes());
			if(val_str.contains("invoke"))
			{
				if(val.getValue() instanceof VirtualInvokeExpr)
				{System.out.println(val.getValue().getClass()+" v/s "+((VirtualInvokeExpr)val.getValue()).getMethod().getSubSignature());
				System.out.println(((VirtualInvokeExpr)val.getValue()).getBase());
				}
			str=val_str.split(" ", 2);
			hm.put(str[1], str[0]);
			}
		}
		return hm;
	}
	
	public HashMap<String,String> getlocalwithtype(Body body)
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		Chain<Local> localpar = body.getLocals();
		for(Local l: localpar)
		{
			if(!l.toString().contains("$"))
				hm.put(l.toString(), l.getType().toString());
		}
	return hm;
	
	}
}