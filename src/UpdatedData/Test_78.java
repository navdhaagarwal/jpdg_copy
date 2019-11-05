package UpdatedData;
import java.io.*;
import java.util.*;

public class Test_78 {
    private static InputReader in;
    private static PrintWriter out;
    public static boolean SUBMIT = true;
    public static final String NAME = "C_large";

    private static void solve() throws IOException {
        long n=in.nextLong(), k=in.nextLong(), l=0, c=1, s=0;
        do {
            s+=c;
            c*=2;
            l++;
        } while (s<k);
        c/=2;
        s-=c;
        long min=(n-s)/(s+1);
        long max=((n-s)%(s+1)==0)?min:min+1;
        long max_count=n-s-min*(s+1);
        long len=(k-s<=max_count)?max:min;
        min=(len-1)/2;
        max=min+(len-1)%2;
        out.println(max +" " + min);
    }

    public static void main(String[] args) throws IOException {
        if (SUBMIT) {
            in = new InputReader(new FileInputStream(new File(NAME + ".in")));
            out = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".out")));
        } else {
            in = new InputReader(System.in);
            out = new PrintWriter(System.out, true);
        }

        int numCases = in.nextInt();
        for (int test = 1; test <= numCases; test++) {
            out.print("Case #" + test + ": ");
            solve();
        }

        out.close();
        System.exit(0);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}

