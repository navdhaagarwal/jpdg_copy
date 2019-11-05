
package UpdatedData;

import java.io.*;
import java.util.*;
import java.math.*;

public class Test_62 {

    InputStream is;
    PrintWriter out;
    
    void solve(){
        //Enter code here utkarsh
        int j, t;
        long n, k, i, d, diff, div, mod;
        t = ni();
        A: for(j = 1; j <= t; j++){
            out.print("Case #" + j + ": ");
            n = nl();
            k = nl();
            d = 1L;
            while(k >= d){
                d *= 2L;
            }
            d /= 2L;
            if(d == 0){
                System.out.println(j);
                return;
            }
            diff = k - d;
            div = n / d;
            mod = n % d;
            if(diff <= mod){
                i = div;
            }else{
                i = div - 1L;
            }
            out.println((i / 2) + " " + ((i / 2) - (1L - (i % 2))));
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {    new Utkarsh().run();    }
    void run() throws FileNotFoundException{
        File fi=new File("input.txt");
        try{
            is=new FileInputStream(fi);
        }catch(FileNotFoundException e){
            System.out.printf("Error %s\n",e);
        }
        File fo=new File("output.txt");
        out=new PrintWriter(fo);
        solve();
        out.flush();
    }
    
    byte input[]=new byte[1024];
    int len=0,ptr=0;
    int readByte(){
        if(ptr>=len){
            ptr=0;
            try {len=is.read(input);}   catch(IOException e)    {throw new InputMismatchException();}
            if(len<=0)  return -1;
        }
        return input[ptr++];
    }
    boolean isSpaceChar(int c)  {return !( c >= 33 && c <= 126 );}
    int skip(){
        int b=readByte();
        while(b!=-1 && isSpaceChar(b))  b=readByte();
        return b;
    }
    
    char nc()   {return (char)skip();}
    String ns(){
        int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!isSpaceChar(b)){
            sb.appendCodePoint(b);
            b=readByte();
        }
        return sb.toString();
    }
    int ni(){
        int n=0,b=readByte();
        boolean minus=false;
        while(b!=-1 && !( (b>='0' && b<='9') || b=='-'))    b=readByte();
        if(b=='-'){
            minus=true;
            b=readByte();
        }
        while(b>='0' && b<='9'){
            n=n*10 + (b - '0');
            b=readByte();
        }
        return minus ? -n : n;
    }
    long nl(){
        long n=0L;
        int b=readByte();
        boolean minus=false;
        while(b!=-1 && !( (b>='0' && b<='9') || b=='-'))    b=readByte();
        if(b=='-'){
            minus=true;
            b=readByte();
        }
        while(b>='0' && b<='9'){
            n=n*10 + (b - '0');
            b=readByte();
        }
        return minus ? -n : n;
    }
    double nd() {return Double.parseDouble(ns());}
    float nf()  {return Float.parseFloat(ns());}
    int[] na(int n){
        int a[]=new int[n];
        for(int i=0;i<n;i++)    a[i]=ni();
        return a;
    }
    char[] ns(int n){
        char c[]=new char[n];
        int i,b=skip();
        for(i=0;i<n;i++){
            if(isSpaceChar(b))  break;
            c[i]=(char)b;
            b=readByte();
        }
        return i==n ? c : Arrays.copyOf(c,i);
    }
}
