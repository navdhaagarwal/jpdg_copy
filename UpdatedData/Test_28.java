package UpdatedData;
import java.util.*;

class Solution {

	private static boolean log = false;

	private static class Result {
		long min;
		long max;

		private Result(long min, long max) {
			this.min = min;
			this.max = max;
		}
	}

	private static HashMap<Long, Result> mMemois = new HashMap<Long, Result>(10000000);

	private static Result get(long n, long k) {
		return mMemois.get(n << 20 + k);
	}

	private static void set(long n, long k, Result value) {
		mMemois.put(n << 20 + k, value);
	}

	private static Result solve(long n, long k) {
		Result result = get(n, k);
		if (result != null) {
			return result;
		}
		// do work
		if (k == 0) {
			result = new Result(n, n);
		} else if (k == 1) {
			result = new Result((n-1)/2, n/2);
		} else if (n <= k) {
			result = new Result(0, 0);
		} else {
			boolean n_odd = n % 2 == 1;
			boolean k_odd = k % 2 == 1;
			// if (n_odd && k_odd) {
			// 	result = solve((n-1)/2, (k-1)/2);
			// } else if (n_odd && !k_odd) {
			// 	result = solve((n-1)/2, k/2);
			// } else if (!n_odd && k_odd) {
			// 	result = solve((n-2)/2, (k-1)/2);
			// } else {
			// 	result = solve(n/2, k/2);
			// }
			if (n_odd && k_odd) {
				result = solve((n-1)/2, (k-1)/2);
			} else if (n_odd && !k_odd) {
				result = solve((n-1)/2, k/2);
			} else if (!n_odd && k_odd) {
				result = solve((n-2)/2, (k-1)/2);
			} else {
				result = solve(n/2, k/2);
			}
		}
		// store result
		set(n, k, result);
		return result;
	}

	public static void main(String [ ] args) {
		Scanner scan = new Scanner(System.in);
		int totalCount = scan.nextInt();

		for (int i = 1; i <= totalCount; i++) {
			long n = scan.nextLong();
			long k = scan.nextLong();
			// if (i == 15) {
			// 	System.out.println("n=" + n);
			// 	System.out.println("k=" + k);
			// 	log = true;
			// }
			mMemois = new HashMap<Long, Result>(1000000);
			Result result = solve(n, k);
			System.out.println("Case #" + i + ": " + result.max + ' ' + result.min);
			// log = false;
		}
	}
}
