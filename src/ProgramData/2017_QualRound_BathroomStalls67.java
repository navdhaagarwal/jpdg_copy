import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;


public class CBathroomStalls2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
	    for (int i = 1; i <= t; ++i) {
	      BigInteger N = in.nextBigInteger();
	      BigInteger K = in.nextBigInteger();
	      BigInteger[] res = lsrs(N, K);
	      
	      System.out.println("Case #" + i + ": " + res[0] + " " + res[1]);
	    }
	    in.close();
	  }
	
	public static BigInteger[] lsrs(BigInteger N, BigInteger K) {
		BigInteger[] res = new BigInteger[2];
		BigInteger deux = BigInteger.ONE.add(BigInteger.ONE);
		
		BigInteger puiss = BigInteger.ONE;
		BigInteger temp = K;
		
		while(temp.compareTo(BigInteger.ONE) > 0)
		{
			temp = temp.divide(deux);
			puiss = puiss.multiply(deux);
		}
		
		BigInteger m1 = N.add(BigInteger.ONE).divide(puiss).subtract(BigInteger.ONE);
		BigInteger m2 = N.divide(puiss);
		
		BigInteger mod = N.mod(puiss).add(BigInteger.ONE);
		BigInteger reste = K.subtract(puiss).add(BigInteger.ONE);
		
		BigInteger min, max;
		if(mod.compareTo(reste) >= 0)
		{
			min = m2.subtract(BigInteger.ONE).divide(deux);
			max = m2.divide(deux);
		}
		else
		{
			min = m1.subtract(BigInteger.ONE).divide(deux);
			max = m1.divide(deux);
		}
		
		res[0] = max;
		res[1] = min;
		
		return res;
	}
	
	
}
