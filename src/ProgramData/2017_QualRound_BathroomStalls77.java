import java.io.*;

public class Stall{
    private Stall next;
    private long nb;
    private long ecart;

    public static long vis;
    public static int cas=0;

    public Stall(String t){
	String[] token;
	token = t.split(" ");
	ecart = Long.parseLong(token[0]);
	vis = Long.parseLong(token[1]);
	nb = 1;
	next = null;
    }

    public Stall(long n, Stall s,long e){
	nb = n;
	next = s;
	ecart = e;
    }
    public void ajoute(long n,long e){
	Stall pos = this;
	Stall prece = null;
	while(pos != null && pos.ecart > e){
	    prece = pos;
	    pos = pos.next;
	}
	if (pos == null){
	    Stall s = new Stall(n,null,e);
	    prece.next = s;
	}else if(pos.ecart == e){
	    pos.nb += n;
	}else{
	    Stall s = new Stall(n,pos,e);
	    prece.next = s;
	}
    }
    
    public long place(Stall a){
	while(a.vis > 0){
	    if(a.vis <= a.nb)
		return a.ecart;
	    a.vis -= a.nb;
	    if(a.ecart%2==1)
		a.ajoute(a.nb*2,a.ecart/2);
	    else{
		a.ajoute(a.nb,a.ecart/2);
		a.ajoute(a.nb,(a.ecart-1)/2);
	    }
	    a = a.next;
	}
	return a.ecart;
    }

    public String aff(){
	cas++;
	long p = place(this);
	String res = "Case #"+cas+": "+(p/2)+" "+((p-1)/2)+"\n";
	return res;
    }

    public String toString(){
	String res = "";
	Stall pos = this;
	while (pos != null){
	    res += "ecart "+pos.ecart+" nb "+pos.nb+" ---> ";
	    pos = pos.next;
	}
	res += "\n";
	return res;
    }

    public static void main(String[] args){
	String chaine = "";
	//lecture
	try{
	    InputStream ips=new FileInputStream(args[0]);
	    InputStreamReader ipsr=new InputStreamReader(ips);
	    BufferedReader br=new BufferedReader(ipsr);
	    String ligne;
	    int nb = Integer.parseInt(br.readLine());
	    while(nb > 0){
		ligne=br.readLine();
		Stall a = new Stall(ligne);
		chaine += a.aff();
		nb--;
	    }
	    br.close();
	}
	catch (Exception e){
	    System.out.println(e.toString());
	}
	
	//ecriture
	try {
	    FileWriter fw = new FileWriter(args[1]);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter fichierSortie = new PrintWriter(bw);
	    fichierSortie.println(chaine);
	    fichierSortie.close();
	    System.out.println("Le fichier " + args[1] + " a été créé.");
	}
	catch (Exception e){
	    System.out.println(e.toString());
	}
    }
}
