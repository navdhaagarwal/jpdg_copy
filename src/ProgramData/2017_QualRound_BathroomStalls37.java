import java.util.Scanner;
import java.util.TreeMap;

public class C_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			System.out.print("Case #" + tc + ": ");

			long N = sc.nextLong();
			long K = sc.nextLong();
			long[] result = solve(N, K);
			System.out.println(result[0] + " " + result[1]);
		}

		sc.close();
	}

	static long[] solve(long N, long K) {
		TreeMap<Long, Long> size2count = new TreeMap<Long, Long>();
		size2count.put(N, 1L);

		long size = N + 1;
		while (true) {
			size = size2count.lowerKey(size);
			long count = size2count.get(size);

			long left = (size - 1) / 2;
			long right = size - 1 - left;

			if (K <= count) {
				return new long[] { right, left };
			}
			K -= count;

			increase(size2count, left, count);
			increase(size2count, right, count);

			if (!size2count.containsKey(left)) {
				size2count.put(left, 0L);
			}
		}
	}

	static void increase(TreeMap<Long, Long> size2count, long size, long delta) {
		if (!size2count.containsKey(size)) {
			size2count.put(size, 0L);
		}
		size2count.put(size, size2count.get(size) + delta);
	}
}
