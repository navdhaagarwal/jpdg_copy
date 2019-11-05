/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codejam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author astorres
 */
public class C {

    public static final String rutaDatos = "C:\\Users\\torresbozzia18\\Desktop\\Tools\\CodeJam\\CodeJam\\";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String problem = "C";
        //String input=problem+"-test";
        //String input=problem+"-small-1-attempt1";
        //String input=problem+"-small-2-attempt0";
        String input=problem+"-large";
        Scanner in = new Scanner(new File(rutaDatos+"in\\"+input+".in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(rutaDatos+"out\\"+input+".out.txt")));
        
        int T = in.nextInt();
        for(int i=0;i<T;i++) {
            
            /*********************************/
            
            long N = in.nextLong();
            long K = in.nextLong();
            
            long cnt = 1;
            long nivel = 1;
            long potencia = 1;
            long rz = N;
            
            while(cnt<K) {
                potencia *= 2;
                cnt += potencia;
                rz = (rz-1)/2;
                nivel++;
            }
            
            long pos = K - (cnt-potencia);
            long cupo = N-(cnt-potencia);
            
            if(cupo-potencia*rz>=pos) rz++;
            
            System.out.println("Case #"+(i+1)+": "+(rz/2)+" "+((rz-1)/2));
            out.println("Case #"+(i+1)+": "+(rz/2)+" "+((rz-1)/2));
            
        }
        
        out.close();
    }
    
}
