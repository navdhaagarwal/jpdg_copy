package UpdatedData;
import java.io.*;
import java.util.*;

public class Test_83 {
    public static final PrintStream out = System.out;
    public static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public int numCases;
    
    public void doCase(int caseNumber) throws Exception {
        String line = in.readLine();
        Scanner scan = new Scanner(line);
        long N = scan.nextLong();
        long K = scan.nextLong();
        
        long powerOfTwoLessThanOrEqualToK = 1;
        long exponent = 0;
        while (powerOfTwoLessThanOrEqualToK * 2 <= K) {
            powerOfTwoLessThanOrEqualToK *= 2;
        }
        long alreadyIn = powerOfTwoLessThanOrEqualToK - 1;
        
        long unfilled = N - alreadyIn;
        long smallerGap = unfilled / powerOfTwoLessThanOrEqualToK;
        long largerGap = smallerGap + 1;
        long numLarger = unfilled - smallerGap * powerOfTwoLessThanOrEqualToK;
        long numSmaller = powerOfTwoLessThanOrEqualToK - numLarger;
        
        if (K <= alreadyIn + numLarger) {
            long min = (largerGap-1) / 2;
            long max = (largerGap-1) - min;
            System.out.println(max + " " + min);
        } else {
            long min = (smallerGap-1) / 2;
            long max = (smallerGap-1) - min;
            System.out.println(max + " " + min);
        }
        
    }
    
    public void run() throws Exception {
        numCases = Integer.parseInt(in.readLine().trim());
        for (int i = 1; i <= numCases; i++) {
            out.print("Case #" + i + ": ");
            doCase(i);
        }
    }
    
    public static void main(String[] args) throws Exception {
        new Test_83().run();
    }

}

