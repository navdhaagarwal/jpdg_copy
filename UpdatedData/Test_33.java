/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UpdatedData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Bohdan
 */
public class Test_33 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C-large.in"));
        PrintWriter out = new PrintWriter(new File("output.txt"));

        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            long M = in.nextLong();
            long n = in.nextLong();
            long[] currL = new long[500];
            long[] quant = new long[500];
            int len = 1;
            quant[0] = 1;
            currL[0] = M;
            long used = 0;
            while (2*used+1<n){
                long[] newL = new long[500];
                long[] newQ = new long[500];
                newL[0] = (currL[0])/2;
                long mind = currL[0]-1-newL[0];
                int pos;
                if (mind == newL[0]){
                    newQ[0] = 2*quant[0];
                    pos = 1;
                } else {
                    newQ[0] = quant[0];
                    newQ[1] = quant[0];
                    newL[1] = mind;
                    pos = 2;
                }
                long maxd;
                for (int i = 1; i<len; i++){
                    maxd = (currL[i])/2;
                    mind = currL[i]-maxd-1;
                    if (maxd == newL[pos-1]){
                        newQ[pos-1]+=quant[i];
                    }
                    else{
                        newL[pos] = maxd;
                        newQ[pos] = quant[i];
                        pos++;
                    }
                    if (mind == newL[pos-1]){
                        newQ[pos-1]+=quant[i];
                    }
                    else{
                        newL[pos] = mind;
                        newQ[pos] = quant[i];
                        pos++;
                    }
                    
                    
                }
                used = used*2+1;
                currL = newL;
                quant = newQ;
                len = pos;
                 
            }
            for (int i = 0; i<len; i++){
                if (used+quant[i]>=n){
                    long max = (currL[i])/2;
                    long min = currL[i]-max-1;                    
                    out.println("Case #"+(t+1)+": "+max+" "+min);
                    break;
                }
                else{
                    used += quant[i];
                }
            }
            
            
        }
        out.close();
    }
    
}

