package UpdatedData;
import java.util.Locale;
import java.util.Scanner;

public class Test_42 {

	class MinMax {
		long min;
		long max;
	}

	void solve(int icase) {
		printf("Case #%d: ", icase);
		long n = sl();
		long k = sl();

		MinMax res = find(n, k);

		printf("%d %d\n", res.max, res.min);
	}

	MinMax find(long n, long k) {
		if (k == 1) {
			MinMax res = new MinMax();
			res.min = (n - 1) / 2;
			res.max = n / 2;
			return res;
		}

		if (n % 2 == 0 && k % 2 == 1) {
			return find((n - 1) / 2, k / 2);
		} else {
			return find(n / 2, k / 2);
		}
	}

	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		new C().repSolve();
	}

	void repSolve() throws Exception {
		scanner = new Scanner(System.in);
		// scanner = new Scanner(new java.io.File(""));

		int ncase = si();
		sline();

		for (int icase = 1; icase <= ncase; icase++) {
			solve(icase);
			// System.err.println("[[ " + icase + " ]]");
		}
	}

	Scanner scanner;

	int si() {
		return scanner.nextInt();
	}

	long sl() {
		return scanner.nextLong();
	}

	String ss() {
		return scanner.next();
	}

	String sline() {
		return scanner.nextLine();
	}

	void printf(String format, Object... args) {
		System.out.printf(format, args);
	}
}
