package UpdatedData;
import java.io.*;

public class Test_24 {

    public static void main(String[] args) throws Exception {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        Map2 parent = new Map2();
        Map2 children = new Map2();
        Tests:
        for (int t = 1, ts = (int) readDouble(in); t <= ts; t++) {
            out.print("Case #");
            out.print(t);
            out.print(": ");
            long n = (long) readDouble(in);
            long k = (long) readDouble(in);
            parent.reset();
            parent.add(1, n);
            while (k > parent.totalCount()) {
                k -= parent.totalCount();
                children.reset();
                for (int i = 0; i < parent.size(); i++) {
                    long count = parent.count(i);
                    long num = parent.num(i) - 1;
                    children.add(count, num - (num / 2));
                    children.add(count, num / 2);
                }
                Map2 swap = parent;
                parent = children;
                children = swap;
            }
            for (int i = 0; i < parent.size() + 1; i++) {
                k -= parent.count(i);
                if (k < 1) {
                    long div = parent.num(i) - 1;
                    long max = div - div / 2;
                    long min = div - max;
                    out.print(max);
                    out.print(' ');
                    out.println(min);
                    continue Tests;
                }
            }
            throw new AssertionError();
        }
        out.flush();
    }

    private static double readDouble(StreamTokenizer in) throws IOException {
        in.nextToken();
        return in.nval;
    }

    private static final class Map2 {
        private final long[] counts = new long[2];
        private final long[] nums = new long[2];
        private int size = 0;

        public void add(long count, long number) {
            for (int i = 0; i < size; i++) {
                if (nums[i] == number) {
                    counts[i] += count;
                    return;
                }
            }
            counts[size] = count;
            nums[size] = number;
            size++;
        }

        public int size() {
            return size;
        }

        public long count(int i) {
            return counts[i];
        }

        public long num(int i) {
            return nums[i];
        }

        public long totalCount() {
            long total = 0;
            for (int i = 0; i < size; i++) {
                total += counts[i];
            }
            return total;
        }

        public void reset() {
            size = 0;
        }
    }

}
