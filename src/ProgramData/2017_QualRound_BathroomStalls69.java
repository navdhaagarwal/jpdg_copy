package j2017.qual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@SuppressWarnings("all")
public class C {

	private static final String FILE_NAME = "C-large";	
	
	private static final String RELATIVE_PATH;
	static {
		String p = C.class.getPackage().getName();
		p = p.replace('.', '\\');
		RELATIVE_PATH = "src\\" + p + "\\" + FILE_NAME;
	}
	
	private Scanner in;
	private PrintStream out;
	
	public C () throws FileNotFoundException {
		in = new Scanner(new File(RELATIVE_PATH + ".in"));	
		out = new PrintStream(RELATIVE_PATH + ".out"); 
			
		int cases = nextInt();	
		for (int i = 1; i <= cases; i++) {
			print("Case #" + i + ": ");
			solve();
			println();
		}	

		out.close();
		System.out.println("\nDONE!");
	}
	
	/**************************** 
	 * 		   Solution
	 ****************************/

	class Gap {
		long count;
		long size;
		Gap(long a, long b) {
			count = a; size = b;
		}
	}
	
	class LL {
		/* shitty custom double linked list */
		class N {
			Gap g;
			N prev;
			N succ;
		}
		
		N head;
		N tail;
		
		Gap poll() {
			N t = head;
			head = head.succ;
			if (tail == t) tail = null;
			return t.g;
		}
		
		void add(Gap g) {
			N n = new N();
			n.g = g;
			if (head == null) {
				head = n;
				tail = n;
			} else {
				N t = tail;
				N p = null;
				while (t.g.size < g.size) {
					p = t;
					t = t.prev;
				}
				if (t.g.size == g.size) {
					t.g.count += g.count;
				} else {
					t.succ = n;
					n.prev = t;
					n.succ = p;
					if (p != null) {
						p.prev = n;
					} else {
						tail = n;
					}
				}
			}
			return;
		}
		
	}
	
	private void solve() {
		long N = nextLong();
		long K = nextLong();
		
		LL queue = new LL();
		queue.add(new Gap(1, N));
		Gap lastGap;
		while (true) {
			lastGap = queue.poll();
			K -= lastGap.count;
			if (K <= 0) break;
			
			if (lastGap.size % 2 == 0) {
				long max = lastGap.size / 2;
				long min = max - 1;
				queue.add(new Gap(lastGap.count, max));
				queue.add(new Gap(lastGap.count, min));
			} else {
				queue.add(new Gap(lastGap.count*2, lastGap.size/2));
			}
		}
		
		
		long max = lastGap.size / 2;
		long min = max - 1;
		if (lastGap.size % 2 != 0) min = max;

		print(String.format("%d %d", max, min));
	}
	
	
	/**************************** 
	 * 		Manage input
	 ****************************/
	private int[] nextArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = nextInt();
		}
		return arr;
	}
	
	private int[][] nextMatrix(int rows, int columns) {
		int[][] mat = new int[rows][columns];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				mat[r][c] = nextInt();
			}
		}
		return mat;
	}
	
	private String next() {
		return in.next();
	}
	private int nextInt() {
		return in.nextInt();
	}
	private long nextLong() {
		return in.nextLong();
	}

	/**************************** 
	 * 		Manage output
	 ****************************/
	private void print(String s) { 
		System.out.print(s);
		out.print(s); 
	}	
	private void print(int i) { 
		System.out.print(i);
		out.print(i); 
	}
	private void print(long l) {
		System.out.print(l);
		out.print(l);
	}
	private void print(Object o) {
		System.out.print(o);
		out.print(o);
	}
	private void println() { 
		System.out.println();
		out.println(); 
	}
	
	public static void main(String[] args) {
		try {
			new C();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
