import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
3
4 2
5 2
6 2

 */
public class c {

	public static void main(String[] args) throws IOException {
		boolean fromFile = true;
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		if(fromFile) {
			in = new FastScanner(new FileInputStream("C-large.in"));
			out = new PrintWriter(new PrintWriter("c-large.out"));
		}
		memo = new HashMap<>();
		int numT = in.nextInt();
		for(int ci = 1; ci <= numT; ci++) {
			long n = in.nextLong();
			long k = in.nextLong();
			//long[] bfans = bf(n, k);
			long[] ans = solve(n, k);
			//if(bfans[0] != ans[0] || bfans[1] != ans[1]) {
			//	System.err.println("ERROR  " + n + "  " + k);
			//}
			out.printf("Case #%d: %d %d\n", ci, ans[0], ans[1]);
			if(fromFile) System.out.println(ci);
		}
		out.close();
	}
	
	
	static long[] solve(long n, long k) {
		long min = 0;
		long max = n;
		while(true) {
			long mid = (min + max) / 2;
			//System.out.println(min + "  " + max + "  " + mid);
			if(min + 1 >= max) {
				if(canDo(n, k, max, true)) return new long[]{max, max};
				if(canDo(n, k, max, false)) return new long[]{max, max - 1};
				if(canDo(n, k, min, true)) return new long[]{min, min};
				return new long[]{min, min - 1};
			}
			if(canDo(n, k, mid, false)) {
				min = mid;
			} else {
				max = mid;
			}
		}
	}
	
	static boolean canDo(long n, long k, long x, boolean y) {
		memo.clear();
		//System.out.printf("Count(n = %d, x = %d, y = %b) = %d\n", n, x, y, count(n, x, y));
		return count(n, x, y) >= k;
	}
	
	static HashMap<Long, Long> memo;
	
	static long count(long n, long x, boolean y) {
		if(n == 0) return 0;
		if(memo.containsKey(n))
			return memo.get(n);
		long tmp = n - 1;
		long a = Math.max(tmp / 2, tmp - tmp / 2);
		long b = Math.min(tmp / 2, tmp - tmp / 2);
		if(a < x) return 0;
		if(a == x && y && b != x) return 0;
		long ans = 1 + count(a, x, y) + count(b, x, y);
		memo.put(n, ans);
		return ans;
	}
	
	static long[] bf(long n, long k) {
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.add(n);
		long max = 0;
		long min = 0;
		for(int i = 0; i < k; i++) {
			long a = pq.poll();
			a--;
			pq.add(a / 2);
			pq.add(a - (a / 2));
			if(i == k - 1) {
				max = Math.max(a / 2, a - a / 2);
				min = Math.min(a / 2, a - a / 2);
			}
		}
		return new long[]{max, min};
	}
	
	static class FastScanner {
	    BufferedReader br;
	    StringTokenizer st;
		
	    public FastScanner(InputStream i) {
	        br = new BufferedReader(new InputStreamReader(i));
	        st = new StringTokenizer("");
	    }
				
	    public String next() throws IOException {
	        if(st.hasMoreTokens())
	            return st.nextToken();
	        else
	            st = new StringTokenizer(br.readLine());
	        return next();
	    }

	    public int nextInt() throws IOException {
	        return Integer.parseInt(next());
	    }
	    public int[] nextIntArray(int n) throws IOException {
	    	int[] arr = new int[n];
	    	for(int i = 0; i < n; i++)
	    		arr[i] = nextInt();
	    	return arr;
	    }
	    public long nextLong() throws IOException {
	        return Long.parseLong(next());
	    }
	    public long[] nextLongArray(int n) throws IOException {
	    	long[] arr = new long[n];
	    	for(int i = 0; i < n; i++)
	    		arr[i] = nextLong();
	    	return arr;
	    }
	    public double nextDouble() throws IOException {
	        return Double.parseDouble(next());
	    }
	    public double[] nextDoubleArray(int n) throws IOException {
	    	double[] arr = new double[n];
	    	for(int i = 0; i < n; i++)
	    		arr[i] = nextDouble();
	    	return arr;
	    }
	    public int[] nextOffsetIntArray(int n) throws IOException {
	    	int[] arr = new int[n];
	    	for(int i = 0; i < n; i++)
	    		arr[i] = nextInt() - 1;
	    	return arr;
	    }
	}
}
