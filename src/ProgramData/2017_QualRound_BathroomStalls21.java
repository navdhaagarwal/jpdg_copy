package rayker.gcj2017;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solver {
    public void solve(InputReader in, PrintWriter out) {

        int nTests = in.nextInt();
        for (int t = 1; t <= nTests; t++) {
            long n = in.nextLong();
            long k = in.nextLong();
            Answer ans = smart(n, k);
            assert ans.equals(stupid(n, k));
            print(t, out, ans);
        }
    }

    private Answer smart(long n, long k) {
        TreeMap<Long, Long> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(n, 1L);

        long max = -1;
        long min = -1;
        for (long i = 0; i < k;) {
            Map.Entry<Long, Long> entry = map.pollFirstEntry();
            i += entry.getValue();

            min = (entry.getKey() - 1) / 2;
            max = entry.getKey() / 2;
            createOrIncrease(map, min, entry.getValue());
            createOrIncrease(map, max, entry.getValue());
        }

        return new Answer(min, max);
    }

    private void createOrIncrease(TreeMap<Long, Long> map, long key, Long value) {
        long curValue = map.getOrDefault(key, 0L);
        map.put(key, curValue + value);
    }

    private Answer stupid(long n, long k) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(n);

        long max = -1;
        long min = -1;
        for (int i = 0; i < k; i++) {
            long tmp;
            do {
                tmp = pq.poll();
            } while (tmp == 0);

            min = (tmp - 1) / 2;
            max = tmp / 2;
            pq.add(min);
            pq.add(max);
        }

        return new Answer(min, max);
    }

    private void print(int t, PrintWriter out, Answer v) {
//        if (v == -1) {
//            out.printf("Case #%d: IMPOSSIBLE%n", t);
//        } else {
            out.printf("Case #%d: %d %d%n", t, v.max, v.min);
//        }
    }
}

class Answer {
    public final long min;
    public final long max;

    Answer(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (min != answer.min) return false;
        return max == answer.max;
    }

    @Override
    public int hashCode() {
        int result = (int) (min ^ (min >>> 32));
        result = 31 * result + (int) (max ^ (max >>> 32));
        return result;
    }
}