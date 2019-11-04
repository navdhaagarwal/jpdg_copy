import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {


	public static void main(String[] args)
	{

		/** problem C*/
		Scanner sc;
		try{
			sc = new Scanner(new File("C-large.in"));
			int nbCases = sc.nextInt();
			String[] res = new String[nbCases];
			for(int i = 1; i<=nbCases; i++){
				BigInteger N = sc.nextBigInteger();
				BigInteger K = sc.nextBigInteger();


				// Since for each "level" (K in 2^k .. 2^(k+1)-1) the difference in spacing remains at most 1
				// we only need to find the such interval containing K,
				// fill N with 2^k-1 patrons, and look whether the remaining patrons
				// all fit in "large" spaces or not

				// find pow = 2^k - 1

				BigInteger pow = BigInteger.ONE;
				BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
				BigInteger K_ = K;
				while( K_.compareTo(BigInteger.ONE) > 0){
					K_ = K_.divide(two);
					pow = pow.multiply(two);
				//	System.out.println(K+" "+K_+" "+pow);
				}
				pow = pow.subtract(BigInteger.ONE);

				// fill N with pow evenly spaced patrons
				BigInteger min = (N.subtract(pow)).divide(pow.add(BigInteger.ONE));
				BigInteger max = N.divide(pow.add(BigInteger.ONE));
				BigInteger nbMax = N.mod(pow.add(BigInteger.ONE)).add(BigInteger.ONE);
				BigInteger remaining = K.subtract(pow);

				if (remaining.compareTo(nbMax) <= 0){
					// last one will go in a max:
					min = (max.subtract(BigInteger.ONE)).divide(two);
					max = max.divide(two);
				} else {//it will go in a min:
					max = min.divide(two);
					min = (min.subtract(BigInteger.ONE)).divide(two);
				}

				System.out.println(N+"\t"+K + "\t" + pow+"\t"+nbMax+"\t"+min+"\t"+max);

				res[i-1] = "CASE #" + i + ": "+max+" "+min;


			}

			BufferedWriter bw = new BufferedWriter(new FileWriter("output"));

			for(int c = 0; c<nbCases; c++){
				bw.write(res[c]+"\n");
			}

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
