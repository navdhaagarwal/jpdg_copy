import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Bathroom {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("C-large.in"));
		int numCases = Integer.parseInt(s.nextLine());
		for(int c = 1;c<=numCases;c++) {
			String caseLine = s.nextLine();
			String[] items = caseLine.split("\\s+");
			long n = Long.parseLong(items[0]);
			long k = Long.parseLong(items[1]);
			System.out.println("Case #" + c + ": " + solve(n,k));
		}
	}
	
	public static Pair solve(long n, long k) {
		PairQueue queue = new PairQueue();
		queue.add(new Pair(n,1));
		Pair p = null;
		while(k>0) {
			p = queue.poll();
			//System.out.println(p.min + ", " + p.max + " " + p.count);
			k-=p.count;
			if(p.min==p.max) {
				if(p.min>0) {
					queue.add(new Pair(p.min,p.count*2));
				}
			} else {
				if(p.max>0) {
					queue.add(new Pair(p.max,p.count));
				}
				if(p.min>0) {
					queue.add(new Pair(p.min,p.count));
				}
			}
		}
		return p;
	}
}
class PairQueue {
	LinkedList<Pair> queue = new LinkedList<Pair>();
	HashMap<Pair,Pair> mems = new HashMap<Pair,Pair>();
	public PairQueue() {
		
	}
	public void add(Pair p) {
		if(mems.containsKey(p)) {
			mems.get(p).count+=p.count;
		} else {
			queue.add(p);
			mems.put(p,p);
		}
	}
	public Pair poll() {
		Pair result = queue.poll();
		mems.remove(result);
		return result;
	}
}
class Pair {
	long min, max, count;
	public Pair(long size, long count) {
		if(size%2==0) {
			this.min = size/2-1;
			this.max = size/2;
		} else {
			this.min = size/2;
			this.max = size/2;
		}
		//System.out.println("Adding " + this.min + "," + this.max + " count=" + count);
		this.count = count;
	}
	public String toString() {
		return max + " " + min;
	}
	public boolean equals(Object o) {
		if(o instanceof Pair) {
			Pair p = (Pair)o;
			return p.min==min && p.max==max;
		}
		return false;
	}
	public int hashCode() {
		return (int)(min + 31*max);
	}
}