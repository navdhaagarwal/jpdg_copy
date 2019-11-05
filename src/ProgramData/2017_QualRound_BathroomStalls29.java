package gcj17.nhnpro.qualification;

import com.sun.org.apache.xpath.internal.operations.Div;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        String inputFilePath = "problem1.in";
        String outputFilePath = "problem1.out";
        if (args.length >= 1) inputFilePath = args[0];
        if (args.length >= 2) outputFilePath = args[1];

        try {
            FileReader fileReader = new FileReader(inputFilePath);
            FileWriter fileWriter = new FileWriter(outputFilePath);

            Scanner in = new Scanner(fileReader);
            int numCases = in.nextInt();
            in.nextLine();
            for (int i = 0; i < numCases; i++) {
               fileWriter.write(ProblemC(i + 1, in));
            }

            fileReader.close();
            fileWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            println("Exception: " + ex.toString());
        }
    }

    public static String ProblemA(int caseNumber, Scanner in) {
        String result = "Case #" + caseNumber + ": ";

        if (in.hasNext()) {
            String S = in.next();
            int K = in.nextInt();
            if(S.indexOf('-') < 0)
            {
                result += "0";
            }

            else
            {
                int idx = S.indexOf('-'), count = 0;

                while (idx >= 0 && idx + K <= S.length())
                {
                    String newS = "";
                    for (int i = 0; i < S.length(); i++) {
                        char c = S.charAt(i);
                        if(i >= idx && i < idx +K)
                            newS+= (c == '+') ? '-':'+';
                        else
                            newS += c;
                    }
                    count++;
                    S = newS + "";
                    idx = S.indexOf('-');
                }

                if(S.indexOf('-') < 0)

                 result += count+"";

                else
                    result += "IMPOSSIBLE";
            }
            println("S" + S + "; K:" + K);
        }

        println(result);
        return result + "\n";
    }

    public static String ProblemB(int caseNumber, Scanner in) {
        String result = "Case #" + caseNumber + ": ";

        if (in.hasNext()) {
            long N = Long.valueOf(in.next());
            byte[] byteArray = (N+"").getBytes();

            int len = byteArray.length;
            for (int i = 0; i < len; i++)
            {
                byteArray[i] -= 48;
            }


            for (int i = len - 1; i > 0; i--)
            {
                if (byteArray[i] < byteArray[i - 1])
                {
                    for (int j = i; j < len; j++)
                        byteArray[j] = 9;
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if (byteArray[j] > 0)
                        {
                            byteArray[j]--;
                            break;
                        }
                        else
                        {
                            byteArray[j] = 9;
                        }
                    }
                }
            }

            String s ="";
            boolean skipZero = true;
            for (int i = 0; i < len; i++)
            {
                if (skipZero)
                {
                    if (byteArray[i] == 0)
                        continue;
                    skipZero = false;

                }
                s += byteArray[i];
            }
            result += s;
        }

        println(result);
        return result + "\n";
    }



    public static String ProblemC(int caseNumber, Scanner in) {
        String result = "Case #" + caseNumber + ": ";
        double Ls = 0, Rs = 0;

        if (in.hasNext()) {
            long N = in.nextLong(), K = in.nextLong();

            if(K < N)
            {
                int po = 1;
                double powerof = Math.pow(2, po);
                while (powerof <= K)
                {
                    po++;
                    powerof = Math.pow(2, po);
                }

              //  if(Math.pow(2,po+1) <= N)
                double dx = Math.floor(((double)(N-K)/( Math.pow(2, po -1))) + 1);

                Ls = dx/2;
                Rs = Ls;
                if(dx % 2== 0)
                    Rs --;
               // result += Ls + " " + powerof;
            }

        }

        result += (int)Math.max(Ls, Rs) + " " + (int)Math.min(Ls, Rs);
        println(result);
        return result + "\n";
    }

    public static String ProblemD(int caseNumber, Scanner in) {
        String result = "Case #" + caseNumber + ": ";

        if (in.hasNext()) {
        }

        println(result);
        return result + "\n";
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + " ");
        }
        println("");
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print("" + arr[i] + " ");
        }
        println("");
    }
}


