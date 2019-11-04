import java.util.*;

public class C {
	
	
	static void add(TreeMap<Long, Long> map, long v, long times) {
		if (map.containsKey(v))
			map.put(v, map.get(v) + times);
		else
			map.put(v, times);
	}
	
	static void remove(TreeMap<Long, Long> map, long v) {
		assert map.containsKey(v);
		assert map.get(v) > 0;
		if (map.get(v) == 1)
			map.remove(v);
		else
			map.put(v, map.get(v) - 1);
	}
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int c = 1; c <= T; c++) {
			long N = sc.nextLong();
			long K = sc.nextLong();
			TreeMap<Long, Long> map = new TreeMap<Long, Long>();
			map.put(N, 1L);
			long aim = K - 1;
			while(aim > 0) {
				long highest = map.lastKey();
				long times = map.get(highest);
				if (aim >= times) {
					map.remove(highest);
					highest--;
					long first = highest / 2;
					long second = highest - first;
					add(map, first, times);
					add(map, second, times);
					aim -= times;
				}
				else
					aim = 0;
			}
			long highest = map.lastKey() - 1;
			System.out.printf("Case #%d: %d %d\n", c, highest - highest/2, highest/2);
		}
		sc.close();
	}

}
