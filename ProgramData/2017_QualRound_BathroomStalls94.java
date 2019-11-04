import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Locale;
import java.util.StringTokenizer;

public class Solution implements Runnable {
	
	private PrintStream out;
	private BufferedReader in;
	private StringTokenizer st;
	
	public void solve() throws IOException {
		long time0 = System.currentTimeMillis();
		
		int t = nextInt();
		for (int test = 1; test <= t; test++) {
			long n = nextLong();
			long k = nextLong();
			long[] answer = solve(n, k);
			out.println("Case #" + test +": " + answer[0] + " " + answer[1]);
		}
		
		System.err.println("time: " + (System.currentTimeMillis() - time0) + " ms.");
	}
	
	private long[] solve(long n, long k) {
		long base = n;
		long cnt0 = 1;
		long cnt1 = 0;
		while (k > cnt1 + cnt0) {
			long ncnt0;
			long ncnt1;
			long nbase = (base - 1) / 2;
			if ((base - 1) % 2 == 0) {
				ncnt0 = 2 * cnt0 + cnt1;
				ncnt1 = cnt1;
			} else {
				ncnt0 = cnt0;
				ncnt1 = cnt0 + 2 * cnt1;
			}
			k -= (cnt1 + cnt0);
			base = nbase;
			cnt0 = ncnt0;
			cnt1 = ncnt1;
		}
		if (k <= cnt1) {
			return new long[] {(base + 1 - 1 + 1) / 2, (base + 1 - 1) / 2};
		} else {
			return new long[] {(base - 1 + 1) / 2, (base - 1) / 2};
		}	
	}

	public double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
	
	public long nextLong() throws IOException {
		return Long.parseLong(next());
	}
	
	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
	
	public String next() throws IOException {
		while (!st.hasMoreTokens()) {
			String line = in.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}
	
	@Override
	public void run() {
		try {
			solve();
			out.close();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	public Solution(String name) throws IOException {
		Locale.setDefault(Locale.US);
		if (name == null) {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintStream(new BufferedOutputStream(System.out));
		} else {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(name + ".in")));
			out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")));
		}
		st = new StringTokenizer("");
	}
	
	public static void main(String[] args) throws IOException {
		new Thread(new Solution(null)).start();
	}
}
