import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class C {

	private static int answer = 0;

	public static void main(String[] args) {
		String inputFile = (args.length > 0) ? args[0] : "input.txt";
		String outputFile = (args.length > 1) ? args[1] : (args.length > 0) ? (inputFile + "out.txt") : "output.txt";
		try {
            Scanner sc = new Scanner(new FileReader(inputFile));
            PrintWriter pw = new PrintWriter(outputFile);
            
            int n = sc.nextInt();
            sc.nextLine();
            for (int c=0; c<n; c++) {
            	String[] s = sc.nextLine().split(" ");
				 
                //System.out.println("Test case " + (c+1) + "...");
                pw.print("Case #" + (c+1) + ": ");
                
                pw.print(solve(s[0], s[1]));
                pw.println();
            }
            pw.flush();
            pw.close();
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
 
	static BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
	static BigInteger THREE = TWO.add(BigInteger.ONE);
	
	static ArrayList<BigInteger> split(BigInteger num){
		BigInteger half = num.divide( TWO );
		ArrayList<BigInteger> intervals = new ArrayList<BigInteger>();
		intervals.add( half );
		intervals.add( num.mod( TWO ).equals( BigInteger.ZERO ) ? half.subtract( BigInteger.ONE ) : half );
		return intervals;
		
	}
	
	static String divide(BigInteger num) {
		ArrayList<BigInteger> intervals = split( num );
		return intervals.get(0).toString() + " " + intervals.get(1).toString();
	}

	static BigInteger findMax(Set<String> numbers) {
		BigInteger max = null;
		for ( String s: numbers ) {
			BigInteger value = new BigInteger(s);
			if ( max == null || value.compareTo(max) > 0 ) {
				max = value;
			}
		}
		return max;
	}
	
    private static String solve(String s1, String s2) {
    	BigInteger n = new BigInteger(s1);
    	BigInteger k = new BigInteger(s2);
    	
    	if ( n.equals(k) ) {
    		return "0 0";
    	}
    	if ( k.equals(BigInteger.ONE )) {
    		return divide( n );
    	}
    	if ( k.equals( TWO )) {
    		return divide( n.divide( TWO ) );
    	}
    	if ( k.equals( THREE )) {
    		return divide( n.mod( TWO ).equals( BigInteger.ZERO ) ? n.divide( TWO ).subtract(BigInteger.ONE) : n.divide( TWO ) );
    	}
    	 
    	HashMap<String, BigInteger> ranks = new HashMap<String, BigInteger>();
    	ranks.put( n.toString(), BigInteger.ONE );
    	 
    	BigInteger i = BigInteger.ZERO;
    	BigInteger m = null;
    	while ( i.compareTo( k ) < 0 ) {
    		
    		
    		m = findMax(ranks.keySet());
	        BigInteger counts = ranks.get(m.toString());
	        i = i.add( counts );
	        ranks.remove( m.toString() );
	        ArrayList<BigInteger> intervals = split( m );
	        BigInteger biggerValue = intervals.get(0);
	        BigInteger smallerValue = intervals.get(1);
	        BigInteger value = ranks.get(biggerValue.toString());
	        if ( value == null ) {
	        	value = BigInteger.ZERO;
	        }
	        value = value.add( counts );
	        ranks.put(biggerValue.toString(), value);
	        
	        value = ranks.get(smallerValue.toString());
	        if ( value == null ) {
	        	value = BigInteger.ZERO;
	        }
	        value = value.add( counts );
	        ranks.put(smallerValue.toString(), value);
	        /*System.out.print(m);
	        System.out.print(" ");
	        System.out.print(i);
	        System.out.print(" ");
	        for ( String s: ranks.keySet() ) {
	        	System.out.print(s);
		        System.out.print(" ");
		        System.out.print(ranks.get(s));
		        System.out.print(" ");
	        }
	        System.out.println(ranks.size());*/
    	}
    	
    	return divide(m);
    }
}
