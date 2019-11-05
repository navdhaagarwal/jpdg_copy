package UpdatedData;

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.awt.geom.*;
import java.io.*;

import static java.lang.Math.*;
import static java.lang.Character.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Long.*;
import static java.lang.System.*;
import static java.util.Arrays.*;

public class Test_29 {
//	static final String FILE_NAME = "C";
//		static final String FILE_NAME = "C-small-2-attempt0";
		static final String FILE_NAME = "C-large";
	static final String PATH = "D:\\Eclipse Workspace\\GCJ\\src\\gcj2017qual\\";

	static int INF = Integer.MAX_VALUE / 2;
	static double EPS = 1e-7;
	long n, k;
	long mmax, mmin;
	TreeMap <Long,Long> set = new TreeMap <Long,Long>();
	public void input() throws Exception {
		String s = in.readLine();
		String[] ss = s.split("[ ]+");
		n = new Long (ss[0]);
		k = new Long (ss[1]);
		System.out.println("n:" + n + ", k:" + k + "."); // print
	}

	public void output() throws IOException {
		out.write("Case #" + (CURRENT_TEST + 1) + ": " + mmax+ " " + mmin + "\n");
		System.out.println("Case #" + (CURRENT_TEST + 1) + ": " + mmax+ " " + mmin + "\n");
	}

	public void process() throws Exception {
		set = new TreeMap <Long,Long>() ;
		set.put(n,1L);
		long sofar = 0;
		while (sofar < k) {
			Map.Entry<Long, Long> topEntry = set.pollLastEntry();
			long top = topEntry.getKey() -1;
			long count = topEntry.getValue();
			sofar += count;
			if (top<0) top = 0;
			mmax = mmin = top/2;
			mmax += top%2;
			
			addToSet (set, mmax, count);
			addToSet (set, mmin, count);
		}
	}

	private void addToSet(TreeMap<Long, Long> set, long x, long count) {
		Long current = set.get(x);
		if (current==null) current= count;
		else current += count;
		set.put(x, current);
	}

	int[] parseArrInt(String s) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String[] ss = s.split("[ ]+");
		for (String x : ss)
			list.add(new Integer(x));
		int[] ret = new int[list.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = list.get(i);
		return ret;
	}

	static BufferedReader in;
	static BufferedWriter out;
	static final String INPUT_FILE_NAME = PATH + FILE_NAME + ".in";
	static final String OUTPUT_FILE_NAME = PATH + FILE_NAME + ".out";
	static int NUMBER_OF_TEST, CURRENT_TEST;

	public static void main(String[] args) throws java.lang.Exception {
		in = new BufferedReader(new FileReader(new File(INPUT_FILE_NAME)));
		out = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE_NAME)));
		NUMBER_OF_TEST = new Integer(in.readLine());
		for (CURRENT_TEST = 0; CURRENT_TEST < NUMBER_OF_TEST; CURRENT_TEST++) {
			CSmall2 instance = new CSmall2();
			instance.input();
			instance.process();
			instance.output();
		}
		in.close();
		out.close();
	}
}

