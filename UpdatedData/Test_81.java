package UpdatedData;
import java.util.Scanner;

public class Test_81 {
	static long[] LR(long n, long k) {
		long[] lr = new long[2];
		if (n*2/3<k) {
			lr[0] = lr[1] = 0L;
		}
		else if (k == 1) {
			n--;
			lr[1] = n/2;
			lr[0] = lr[1] + n%2;
		}
		else {
			n--;
			k--;
			if (k%2 == 1)
				lr = LR(n/2 + n%2, k/2 + k%2);
			else lr = LR(n/2, k/2 + k%2);
		}		
		return lr;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int t = 0; t < T; t++) {
			long n = scan.nextLong();
			long k = scan.nextLong();
			long[] lr = LR(n, k);
			System.out.printf("Case #%d: %d %d\n", t+1, lr[0], lr[1]);
		}

	}

}

