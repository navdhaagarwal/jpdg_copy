package UpdatedData;
import java.util.*;

public class Test_44 {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int numCases = stdin.nextInt();

		for (int loop=1; loop<=numCases; loop++) {

			long n = stdin.nextLong();
			long k = stdin.nextLong();
			long[] res = solve(n, k);
			System.out.println("Case #"+loop+": "+res[0]+" "+res[1]);
		}
	}

	public static long[] solve(long n, long k) {

		long[] res = new long[2];
		n--;
		res[0] = n - n/2;
		res[1] = n/2;
		if (k == 1) return res;

		TreeMap<Long,Long> map = new TreeMap<Long,Long>();
		map.put(res[0], 1L);
		if (res[1] != res[0])map.put(res[1], 1L);
		else map.put(res[0], 2L);

		k--;
		for (long i=2; k>i; i = (i<<1)) {

			TreeMap<Long,Long> tmp = new TreeMap<Long,Long>();
			for (long x : map.keySet()) {
				if (x%2 == 1) {
					long start = tmp.containsKey(x/2) ? tmp.get(x/2) : 0;
					tmp.put(x/2, start+2*map.get(x));
				}
				else {
					long start = tmp.containsKey(x/2) ? tmp.get(x/2) : 0;
					tmp.put(x/2, start+map.get(x));
					start = tmp.containsKey(x/2-1) ? tmp.get(x/2-1) : 0;
					tmp.put(x/2-1, start+map.get(x));
				}
			}

			map = tmp;
			k -= i;
		}

		long val = map.get(map.lastKey()) >= k ? map.lastKey() : map.firstKey();
		res[0] = val/2;
		res[1] = (val-1)/2;
		return res;
	}

}

