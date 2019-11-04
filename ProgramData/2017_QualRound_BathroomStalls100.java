import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class C {

	private static final String IN_FILE = "C-large.in";
	private static final String OUT_FILE = "file.out";

	private static BufferedReader in;
	private static PrintWriter out;

	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new FileReader(IN_FILE));
		out = new PrintWriter(new FileWriter(OUT_FILE));

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++)
			workCase(t + 1, in.readLine());

		out.close();
		System.exit(0);
	}

	private static Queue<Ob> queue;
	private static Map<Long, Ob> seen;

	private static void workCase(int caseNumber, String line) {
		queue = new PriorityQueue<Ob>(10, new Comparator<Ob>() {

			public int compare(Ob a, Ob b) {
				if (a.l < b.l)
					return 1;
				else if (a.l == b.l)
					return 0;
				else
					return -1;
			}

		});
		seen = new HashMap<Long, Ob>();
		String[] sa = line.split(" ");
		queue.add(new Ob(Long.parseLong(sa[0]), 1));
		seen.put(Long.parseLong(sa[0]), queue.peek());
		long people = Long.parseLong(sa[1]);
		long peopleServed = 0;
		long lastY = -1;
		long lastZ = -1;
		while (peopleServed < people) {
			Ob ob = queue.poll();
			peopleServed += ob.num;
			seen.remove(ob.l);
			long n = ob.l;
			if (n % 2 == 1) {
				add(n / 2, ob.num * 2);
				lastY = n / 2;
				lastZ = n / 2;
			} else {
				add(n / 2, ob.num);
				add(n / 2 - 1, ob.num);
				lastY = n / 2;
				lastZ = n / 2 - 1;
			}
		}
		out.println("Case #" + caseNumber + ": " + lastY + " " + lastZ);
	}

	private static void add(long l, long num) {
		if (seen.containsKey(l)) {
			seen.get(l).num += num;
		} else {
			Ob ob = new Ob(l, num);
			seen.put(l, ob);
			queue.add(ob);
		}
	}

	private static class Ob {
		long l;
		long num;

		public Ob(long l, long num) {
			this.l = l;
			this.num = num;
		}
	}

}
