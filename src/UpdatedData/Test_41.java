package UpdatedData;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_41 {
	private static boolean debugFlag = true;

	// Only if input contains all problems at once
	public static void solveAll(InputStream in, PrintWriter printer) {
		try (Scanner scanner = new Scanner(in)) {
			int count = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < count; i++) {
				solveOne(i + 1, scanner, printer);
			}
		}
	}

	private static void solveOne(int i, Scanner scanner, PrintWriter printer) {
		// TODO: Parse input
		long n = scanner.nextLong();
		long k = scanner.nextLong();
		Solution solution = solve(n, k);
		printer.println("Case #" + i + ": " + solution);
	}

	public static Solution solve(long n, long k) {
		debug("Solving N=%d, K=%d", n, k);
		Queue<Gap> q = new LinkedList<Gap>();
		Gap lastGap = new Gap(n, 1);
		q.add(lastGap);
		int rounds = 0;
		while (true) {
			rounds++;
			Gap gap = q.poll();
			if (gap.size == 0)
				throw new RuntimeException("Negative gap size");
			debug("Found %d gaps of size %d", gap.count, gap.size);
			// 5 => 2 + 2
			// 4 => 2 + 1
			long max = gap.size / 2;
			long min = (gap.size - 1) / 2;
			debug("Split in %d x %d and %d", gap.count, max, min);
			if (gap.count >= k) {
				debug("Solution in %d rounds", rounds);
				return new Solution(max, min);
			}
			k -= gap.count;
			if (lastGap.size == max) {
				lastGap.add(gap.count);
			} else {
				lastGap = new Gap(max, gap.count);
				q.add(lastGap);
			}
			if (lastGap.size == min) {
				lastGap.add(gap.count);
			} else {
				lastGap = new Gap(min, gap.count);
				q.add(lastGap);
			}
			debug("%s", q);
		}
	}

	private static class Gap {
		final long size;
		long count;

		public Gap(long size, long count) {
			this.size = size;
			this.count = count;
		}

		public void add(long extraCount) {
			count += extraCount;
		}

		@Override
		public String toString() {
			return count + "x" + size;
		}
	}

	private static class Solution {
		final long max;
		final long min;

		public Solution(long max, long min) {
			this.max = max;
			this.min = min;
		}

		@Override
		public String toString() {
			return max + " " + min;
		}
	}

	private static void debug(String string, Object... params) {
		if (debugFlag)
			System.err.printf(string + "\n", params);
	}
}

