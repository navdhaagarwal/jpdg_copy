package UpdatedData;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Test_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        outer:
        for (int caseNum = 1; caseNum <= numCases; caseNum++) {
            long N = sc.nextInt();
            long K = sc.nextInt();

            SortedMap<Long, Long> sizeToCount = new TreeMap<>(Comparator.reverseOrder());
            sizeToCount.put(N, 1L);

            System.out.print("Case #" + caseNum + ": ");
            long num = 0;
            for (long i = 0; num < K; i++) {
                long pow = (1 << i);
                if (num + pow >= K) {
                    long total = num;
                    for (Map.Entry<Long, Long> entry : sizeToCount.entrySet()) {
                        if (total + entry.getValue() >= K) {
                            long key = entry.getKey();
                            System.out.format("%s %s%n", key / 2, (key - 1) / 2);
                            continue outer;
                        } else {
                            total += entry.getValue();
                        }
                    }
                } else {
                    SortedMap<Long, Long> newSizeToCount = new TreeMap<>(Comparator.reverseOrder());
                    for (Map.Entry<Long, Long> entry : sizeToCount.entrySet()) {
                        long size = entry.getKey();
                        long a = (size - 1) / 2, b = size / 2;
                        if (a > 0) {
                            newSizeToCount.put(a, newSizeToCount.getOrDefault(a, 0L) + entry.getValue());
                        }
                        if (b > 0) {
                            newSizeToCount.put(b, newSizeToCount.getOrDefault(b, 0L) + entry.getValue());
                        }
                    }
                    sizeToCount = newSizeToCount;
                    num += pow;
                }
            }

        }
    }
}

