package UpdatedData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Test_43 {

	private static final String PROBLEM = "C";
	private static final String TYPE = "large";
	private static final String INPUT = String.format("%s-%s.in", PROBLEM, TYPE);
	private static final String OUTPUT = String.format("%s-%s.out", PROBLEM, TYPE);
	private static final String RES_FORMAT = "Case #%d: %s";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(INPUT));
		int ttt = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= ttt; i++) {
			String solution = solve(br);
			String formatted = String.format(RES_FORMAT, i, solution);
			sb.append(formatted);
			sb.append(System.getProperty("line.separator"));
			System.out.println(formatted);
		}
		PrintWriter pw = new PrintWriter(OUTPUT);
		pw.println(sb.toString());
		pw.flush();
		pw.close();
	}

	private static String solve(BufferedReader br) throws IOException {
		String[] in = br.readLine().split(" ");
		long n = Long.parseLong(in[0]);
		long k = Long.parseLong(in[1]);

		TreeMap<Long, Long> map = new TreeMap<>(Comparator.reverseOrder());
		map.put(n, 1L);
		while (true) {
			Map.Entry<Long, Long> firstEntry = map.pollFirstEntry();
			long size = firstEntry.getKey();
			if (firstEntry.getValue() >= k) {
				return (size / 2) + " " + ((size - 1) / 2);
			} else {
				k -= firstEntry.getValue();
				add(map, size / 2, firstEntry.getValue());
				add(map, (size - 1) / 2, firstEntry.getValue());
			}
		}
	}

	private static void add(TreeMap<Long, Long> map, long size, Long count) {
		Long currCount = map.get(size);
		if (currCount == null) {
			map.put(size, count);
		} else {
			map.put(size, currCount + count);
		}
	}
}

