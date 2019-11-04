import java.util.*;
import java.io.*;

public class PC implements Runnable {
    static class Data {
        private StringTokenizer tok;
        private final BufferedReader rd;
        private final PrintWriter wr;

        Data() {
            tok = null;
            rd = new BufferedReader(new InputStreamReader(System.in));
            wr = new PrintWriter(System.out);
        }

        Data(String name) throws IOException {
            tok = null;
            rd = new BufferedReader(new FileReader(new File(name + ".in")));
            wr = new PrintWriter(new File(name + ".out"));
        }

        void println(String line) {
            wr.println(line);
        }

        void close() throws IOException {
            rd.close();
            wr.close();
        }

        String nextToken() throws IOException {
            while (tok == null || !tok.hasMoreTokens()) {
                tok = new StringTokenizer(rd.readLine());
            }
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        long nextLong() throws IOException {
            return Long.parseLong(nextToken());
        }

        static Data console() {
            return new Data();
        }

        static Data files(String name) throws IOException {
            return new Data(name);
        }
    }

    public static void main(String[] args) {
        new Thread(new PC()).start();
    }

    @Override
    public void run() {
        try {
            solve();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void solve() throws IOException {
        Data data = Data.files("c");

        int t = data.nextInt();
        for (int i = 0; i < t; ++i) {
            long n = data.nextLong();
            long k = data.nextLong();
            String res = subsolve(n, k);
            data.println(String.format("Case #%d: %s", i + 1, res));
        }

        data.close();
    }

    static class State {
        final private TreeMap<Long, Long> vs;

        State() {
            vs = new TreeMap<>();
        }

        void add(long size) {
            update(size, 1);
        }

        private void update(long size, long delta) {
            long cnt = getOrElse(size, 0) + delta;
            if (cnt < 0) {
                throw new AssertionError();
            } else if (cnt == 0) {
                vs.remove(size);
            } else {
                vs.put(size, cnt);
            }
        }

        private long getOrElse(long size, long def) {
            Long res = vs.get(size);
            if (res != null) {
                return res;
            }
            return def;
        }

        long solve(long n, long k) {
            add(n);
            long rem = k;

            while (true) {
                Map.Entry<Long, Long> e = vs.lastEntry();
                long maxSize = e.getKey();
                long maxCnt = e.getValue();

                if (rem <= maxCnt) {
                    return maxSize;
                }

                rem -= maxCnt;
                update(maxSize, -maxCnt);

                long leftSize = (maxSize - 1) / 2;
                long rightSize = (maxSize - 1 - leftSize);
                if (leftSize > 0) {
                    update(leftSize, maxCnt);
                }
                if (rightSize > 0) {
                    update(rightSize, maxCnt);
                }
            }
        }
    }

    private String result(long size) {
        long leftSize = (size - 1) / 2;
        long rightSize = (size - 1 - leftSize);
        long maxv = Math.max(leftSize, rightSize);
        long minv = Math.min(leftSize, rightSize);
        return String.format("%d %d", maxv, minv);
    }

    private String subsolve(long n, long k) {
        State state = new State();
        return result(state.solve(n, k));
    }
}
