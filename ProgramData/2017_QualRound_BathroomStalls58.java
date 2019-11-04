package com.gplex.year2017.world.qualification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Bathroom_Stalls_large {
	private static final Logger logger = LoggerFactory.getLogger(Bathroom_Stalls_large.class); private static Scanner sc; 	private static PrintWriter pw;	private static File in, out;
	public static void init(){ try {  String path = "src/main/java/" + Bathroom_Stalls_large.class.getCanonicalName().replace(".", "/"); in = new File(path + "_in.txt"); in.createNewFile(); sc = new Scanner(in); out = new File(path +"_out.txt"); pw = new PrintWriter(new FileWriter(out));}catch(Exception e){logger.error("",e);}}
    public static void closeFiles(){ pw.flush(); pw.close(); sc.close();}	
    public static long gcd(long a, long b) { if (b==0) return a;  return gcd(b,a%b);} public static long lcm(long a, long b){ return a * (b / gcd(a, b));}
   public static Set<String> cache;
	public static long max;
	public static long min;

	public static void divPpl(long s, long ppl){
		String k = s+"->"+ppl;

		long r1 = s / 2;
		long r2 = s - r1;
		if (r1 >= r2) {
			r1 -= 1;
		} else {
			r2 -= 1;
		}
		long mn = Math.min(r1, r2);
		long mx = Math.max(r1, r2);
        logger.debug("key = {} max = {} min = {}", k, mx, mn);
        if(ppl == 1){
            max = mx;
            min = mn;
            return;
        }
        boolean even = (ppl-1)%2 == 0;
        if(even){
           divPpl(mn,(ppl-1)/2);
        }else{
           divPpl(mx,(ppl-1)/2 + 1);
        }
	}


	public static void main(String[] args) throws Throwable{
	    init();
		int numberOfTestCases =  sc.nextInt(10);
		//numberOfTestCases = 1;
		
		for(int caseNum=0;caseNum<numberOfTestCases;caseNum++){
			cache = new HashSet<>();
			long s = sc.nextLong();
			long p = sc.nextLong();
			long people = p;
			min = s;
			max = 0;

			divPpl(s, p);

			String result = String.format("Case #%d: %s", caseNum+1,max + " "+ min);
			pw.println(result);
			logger.debug("{}\n\n", new Object[]{result});
		
		}
   		
	    closeFiles();
 		
	}

}
