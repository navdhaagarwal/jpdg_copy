
import java.io.*;
import java.util.*;

public class C {
    // private static final String FIN = "C-small-1-attempt0.in";
    // private static final String FOUT = "C-small-1-attempt0.out";
    // private static final String FIN = "C-small-2-attempt0.in";
    // private static final String FOUT = "C-small-2-attempt0.out";
    private static final String FIN = "C-large.in";
    private static final String FOUT = "C-large.out";

    public static void solution(BufferedReader reader, PrintWriter out)
            throws IOException {
        In in = new In(reader);
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            long N = in.nextLong(), K = in.nextLong();
            PriorityQueue<Long> queue = new PriorityQueue<Long>();
            HashMap<Long, Long> cnt = new HashMap<Long, Long>();
            queue.add(-N);
            cnt.put(N, 1L);
            long min = 0, max = 0;
            while (K > 0) {
                long len = -queue.poll();
                long c = cnt.remove(len);
                min = (len - 1) / 2;
                max = len / 2;
                if (!queue.contains(-min))
                    queue.add(-min);
                if (!cnt.containsKey(min))
                    cnt.put(min, 0L);
                cnt.put(min, cnt.get(min) + c);
                if (!queue.contains(-max))
                    queue.add(-max);
                if (!cnt.containsKey(max))
                    cnt.put(max, 0L);
                cnt.put(max, cnt.get(max) + c);
                K -= c;
            }
            out.printf("Case #%d: %d %d\n", t, max, min);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(FIN));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(FOUT)));
        solution(reader, out);
        out.close();
    }

    protected static class In {
        private BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");

        public In(BufferedReader reader) {
            this.reader = reader;
        }

        public String next() throws IOException {
            while (!tokenizer.hasMoreTokens())
                tokenizer = new StringTokenizer(reader.readLine());
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}
