// https://code.google.com/codejam/contest/3264486/dashboard#s=p2
package UpdatedData;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Test_61 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int tc = s.nextInt();
        for (int t = 1; t <= tc; t++) {
            long n = s.nextLong();
            long k = s.nextLong();
            long[] sizes = solve(n, k);
            System.out.println("Case #" + t + ": " + sizes[0] + " " + sizes[1]);
        }
    }

    private static long[] solve(long n, long k) {
        TreeMap<Long, Long> intervals = new TreeMap<>(Comparator.<Long> naturalOrder().reversed());
        intervals.put(n, 1L);

        while (k > 0) {
            Entry<Long, Long> entry = intervals.firstEntry();
            long length = entry.getKey();
            long count = entry.getValue();
            long[] split = split(length);

            if (count >= k) {
                return split;
            }
            k -= count;
            intervals.remove(length);
            intervals.merge(split[0], count, (a, b) -> a + b);
            intervals.merge(split[1], count, (a, b) -> a + b);
        }

        return null;
    }

    private static long[] split(long n) {
        if (n % 2 == 0) {
            return new long[] { n / 2, n / 2 - 1 };
        }
        return new long[] { n / 2, n / 2 };

    }

}

