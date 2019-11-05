package UpdatedData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Test_74 {
	public static void main(String args[]) throws FileNotFoundException {
		String filename = "C-large";
		new C2().run(
				new Scanner(new File(filename + ".in")),
				System.out);
	}

	void run(Scanner scanner, PrintStream out) {
		int T = scanner.nextInt();
		for(int i=0; i<T; i++) {
			long N = scanner.nextLong();
			long K = scanner.nextLong();
			long[] result = solve(N, K);
			out.println("Case #" + (i+1) + ": " + result[1] + " " + result[0]);
		}
		scanner.close();
	}

	/**
	 * @param N N+2 bathroom
	 * @param K K people
	 */
	long[] solve(long N, long K) {
		if(K == 1) {
			return new long[] { (N-1)/2, N/2 };
		}
		
		if(N % 2 == 0 && K % 2 == 0) {
			// N et K pairs
		}
		else {
			N--;
		}
		return solve(N/2, K/2);
	}
	
}

