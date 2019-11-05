import java.io.*;
import java.util.*;

public class C {
	private static class Range implements Comparable<Range> {
		long a, b;

		Range(long a, long b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Range r) {
			if (b - a == r.b - r.a) {
				return Long.compare(a, r.a);
			} else {
				// Larger is better, so put them first
				return -Long.compare(b - a, r.b - r.a);
			}
		}

		@Override
		public String toString() {
			return "(" + a + ", " + b + ")";
		}
	}
	public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		StringBuilder output = new StringBuilder();
		String newLine = System.lineSeparator();

		int t = Integer.parseInt(tokenizer.nextToken());
		for (int testCase = 1; testCase <= t; testCase++) {
			tokenizer = new StringTokenizer(input.readLine());
			long n = Long.parseLong(tokenizer.nextToken());
			long k = Long.parseLong(tokenizer.nextToken());
			TreeSet<Range> customers = new TreeSet<Range>();
			Range largest = new Range(0,n-1);
			long pos = 0;
			customers.add(largest);

			for (int i = 0; i < k; i++) {
				largest = customers.pollFirst();
				pos = (largest.a + largest.b) >>> 1;
				if (largest.a <= pos - 1) {
					customers.add(new Range(largest.a, pos - 1));
				}
				if (pos + 1 <= largest.b) {
					customers.add(new Range(pos + 1, largest.b));
				}
			}
			long ls = pos - largest.a;
			long rs = largest.b - pos;

			output.append("Case #" + testCase + ": " + Math.max(ls, rs) + " " + Math.min(ls, rs));
			output.append(newLine);
		}

		System.out.print(output.toString());
	}
}
