package UpdatedData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
/*

5
4 2
5 2
6 2
1000 1000
1000 1


.*..

*/
public class Test_30 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		in = new Scanner(new FileInputStream("clarge3.in"));
		out = new PrintWriter(new FileOutputStream("clarge3.out"));
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			long n = in.nextLong(), k = in.nextLong();
//			long minMin;
//			for(minMin=n-1;minMin>=0;minMin--) {
//				memo1 = new HashMap<>();
//				long count = count(n, minMin);
//				if(count >= k) break;
//			}
			long lo = 0, hi = n;
			while(lo < hi) {
				long mid = (lo+hi+1)/2;
				memo1 = new HashMap<>();
				long count = count(n,mid);
				if(count >= k) {
					lo = mid;
				} else {
					hi = mid-1;
				}
			}
			
			memo1 = new HashMap<>();
			long count1 = k-count(n, lo+1);
			memo2 = new HashMap<>();
			long ans2 = 0;
			if(count1 <= count2(n,lo)) {
				ans2 = lo+1;
			} else {
				ans2 = lo;
			}
			out.printf("Case #%d: %d %d\n", t, ans2, lo);
			System.out.println("Done " + t);
			
		}

		out.close();
	}
	static HashMap<Long, Long> memo1;
	static HashMap<Long, Long> memo2;
	static long count(long n, long minMin) {
		if(memo1.containsKey(n)) return memo1.get(n);
		if(n == 0) return 0;
		long left = (n-1)/2;
		long right = (n/2);
		long ans = 0;
		if(Math.min(left,right) >= minMin)
			ans++;
		ans += count(left, minMin);
		ans += count(right, minMin);
		memo1.put(n, ans);
		return ans;
	}
	static long count2(long n, long minMin) {
		if(memo2.containsKey(n)) return memo2.get(n);
		if(n == 0) return 0;
		long left = (n-1)/2, right = n/2;
		long ans = 0;
		if(Math.min(left,right) == minMin) {
			if(Math.max(left,right) == minMin+1)
				ans++;
		}
		ans += count2(left, minMin);
		ans += count2(right, minMin);
		memo2.put(n, ans);
		return ans;
	}
}

