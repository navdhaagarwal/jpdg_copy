import java.util.Scanner;
import java.util.TreeMap;

public class c
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int a = 0; a < t; a++)
		{
			long n = scan.nextLong();
			long k = scan.nextLong();
			TreeMap<Long, Long> map = new TreeMap<>();
			map.put(n, 1L);
			long total = 0;
			long min = 0, max = 0;
			while (total < k)
			{
				long gap = map.floorKey(Long.MAX_VALUE);
				long occ = map.get(gap);
				map.remove(gap);
				min = (gap-1)/2;
				max = gap-1-min;
				if (!map.containsKey(min))
					map.put(min, 0L);
				map.put(min, map.get(min)+occ);
				
				if (!map.containsKey(max))
					map.put(max, 0L);
				map.put(max, map.get(max)+occ);
				total += occ;
			}
			System.out.printf("Case #%d: %d %d\n", a+1, max,  min);
		}
	}
}
