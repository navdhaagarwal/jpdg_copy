import java.util.*;
import java.io.*;

public class C {

	void solve() {
		long N = in.nextLong();
		long K = in.nextLong();
		System.out.println(N + " " + K);
		//solve((int)N, (int)K);
		long k = K;
		long b = 1;
		long s = 0;
		//System.out.print(K + " = ");
		while (k > b) {
			//System.out.print(b + " + ");
			k -= b;
			s += b;
			b *= 2;
		}
		//System.out.println(k);
		//System.out.println(s);
		//System.out.println(b);
		long L = N - s - b;
		//System.out.println("L = "+L);
		long ll = L - (L/b)*b;
		//System.out.println("ll = "+ll);
		long total = (k <= ll)?L/b+1:L/b;
		//System.out.println(total);
		System.out.println((total - 
				total/2)+" "+(total/2));
		out.println((total - 
				total/2)+" "+(total/2));
	}

	
	void solve(int N, int K) {
		boolean[] o = new boolean[N];
		int aa = -1;
		int bb = -1;
		for (int k = 0; k < K; k++) {
			int[] left = new int[N];
			int[] right = new int[N];
			for (int s = 0; s < N; s++) {
				if (o[s]) {
					left[s] = -1;
				} else {
					left[s] = (s==0)?0:left[s-1]+1;
				}
			}
			for (int s = N-1; s >=0; s--) {
				if (o[s]) {
					right[s] = -1;
				} else {
					right[s] = (s==N-1)?0:right[s+1]+1;
				}
			}
			
			int[] a = new int[N];
			int[] b = new int[N];
			int maxa = -1;
			int maxa_s = -1;
			int maxa_c = 0;
			for (int s = 0; s < N; s++) {
				if (o[s]) {
					a[s] = -1;
					b[s] = -1;
				} else {
					a[s] = Math.min(left[s], right[s]);
					b[s] = Math.max(left[s], right[s]);
					if (maxa == a[s]) {
						maxa_c += 1;
					}
					if (maxa < a[s]) {
						maxa = a[s];
						maxa_s = s;
						maxa_c = 0;
					}
				}
			}
			
			if (maxa_c == 1) {
				o[maxa_s] = true;
				aa = a[maxa_s];
				bb = b[maxa_s];
			} else {
				int maxb = -1;
				int maxb_s = -1;
				for (int s = 0; s < N; s++) {
					if (a[s] == maxa) {
						if (maxb < b[s]) {
							maxb = b[s];
							maxb_s = s;
						}
					}
				}
				o[maxb_s] = true;
				aa = a[maxb_s];
				bb = b[maxb_s];
			}
			//System.out.println(k +" " +bb + " " + aa);
		}
		System.out.println(bb + " " + aa);
	}


	/*************************************************************************/

	public static void main(String[] args) throws Exception {
		main1();
	}

	public static void main0() {
		String s = "";
		solveProblem0(s);
	}

	public static void main1() throws Exception {
		String load = "C:/Users/dmytrobukhantsov/Downloads/C-large.in";
		String save = "C:/Users/dmytrobukhantsov/Downloads/C-large.out";
		solveProblem1(load, save);
	}

	/*************************************************************************/

	public static void solveProblem0(String s) {
		in = new Scanner(s);
		out = new PrintWriter(System.out);
		solveAllCases();
		out.flush();
	}

	public static void solveProblem1(String load, String save) throws FileNotFoundException {
		in = new Scanner(new File(load));
		out = new PrintWriter(new File(save));
		solveAllCases();
		out.close();
	}

	/*************************************************************************/

	static void solveAllCases() {
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			out.print("Case #" + t + ": ");
			System.out.println(String.format("Case #%d: ............", t));
			(new C()).solve();
		}
	}

	static Scanner in;
	static PrintWriter out;
}