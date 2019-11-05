package codejam.f2017;

import java.util.Scanner;
import java.util.TreeMap;

public class C {
	
	private static void insert(TreeMap gaps, long gapsize, long count) {
		Long oldCount = (Long) gaps.get(gapsize);
		if (oldCount != null) {
			count += oldCount.longValue();
		}
		gaps.put(gapsize, count);
	}	
	
	public static void solve(int n, Scanner sc) {
		TreeMap gaps = new TreeMap();
		long N = sc.nextLong();
		long K = sc.nextLong();
		long left = K;

		insert(gaps,N,1);
		
		while(true) {
			long gapsize = (Long) gaps.lastKey();
			long count = (Long) gaps.remove(gapsize);
			if (left <= count) {
				System.out.println("Case #"+n+": "+(gapsize/2)+" "+((gapsize-1)/2));
				return;
			}

			insert(gaps,(gapsize/2),count);
			insert(gaps,((gapsize-1)/2),count);
			left -= count;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int N = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			solve(i+1,sc);
		}
	}

}
