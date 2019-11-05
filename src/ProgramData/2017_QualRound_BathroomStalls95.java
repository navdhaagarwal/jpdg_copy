

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;

// Using the Google Guava library.
public class C {

	public static void main(String [] args) throws IOException {
		String inputFile = "src/C-large.in";
		Scanner in = new Scanner(new File(inputFile));
		PrintStream out = new PrintStream(inputFile.substring(0, inputFile.length()-2)+"out");
		int cases = in.nextInt();
		for (int cs = 1; cs <= cases; cs++) {
		    long n = in.nextLong();
		    long k = in.nextLong() - 1;
		    TreeMap<Long,Long> m = new TreeMap<>();
		    m.put(n, 1L);
		    while (k > 0) {
		        Long high = m.lastKey();
		        Long val = m.get(high);
		        long left = (high - 1) / 2;
		        long right = high / 2;
		        long assigned = Math.min(k, val);
		        add(m, left, assigned);
		        add(m, right, assigned);
		        add(m, high, -assigned);
		        k -= assigned;
		    }
		    Long high = m.lastKey();
            long left = (high - 1) / 2;
            long right = high / 2;

			String ans = "Case #"+cs+": "+right+" "+left;
			out.println(ans);
			System.out.println(ans);
		}
		out.close();
		in.close();
	}

	private static long get(TreeMap<Long,Long> m, Long key) {
	    Long val = m.get(key);
	    return val == null ? 0 : val;
	}

	private static void add(TreeMap<Long,Long> m, Long key, long val) {
	    val = get(m, key) + val;
	    if (val == 0) {
	        m.remove(key);
	    } else {
	        m.put(key, val);
	    }
    }
}
