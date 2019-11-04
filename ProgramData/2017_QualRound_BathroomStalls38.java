import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class QR_C {
public static void main(String[] args) {
		String prblm="C"; boolean fl=true;
		String filein=prblm+"-"+((fl)?"large":"small")+".in.txt";
		String fileout=prblm+"-"+((fl)?"large":"small")+".out.txt";
		try {
			BufferedReader fr= new BufferedReader(new FileReader(filein));
			BufferedWriter fw= new BufferedWriter(new FileWriter(fileout));
			String s=fr.readLine();
			int T=Integer.parseInt(s);
			for (int i = 1; i <= T; i++) {
				s=fr.readLine();
				String[] tok=s.split(" ");
				Long N=Long.parseLong(tok[0]);
				Long K=Long.parseLong(tok[1]);
				Long nl=1l,ns=0l;
				while (N>0){
					if (2*(ns+nl)>K) break;
					if (N%2l==1) {nl+=nl+ns;}
					else {ns+=nl+ns;}				
					N/=2;					
				}
				Long lf=K-(ns+nl-1);
				if (lf>nl) N--;
				//System.out.println("---"+K+" "+lf+" "+N+" "+nl);
				Long mx=N/2,mn=(N-1)/2;
				System.out.println(mx+" "+mn);
				fw.write("Case #"+i+": "+ mx+" "+mn  +"\n");
			}
			fr.close();
			fw.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
}