package UpdatedData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test_48 {
    private static final Path input = Paths.get("C.in");
    private static final Path output = Paths.get("C.out");

    public static void main(String[] args) throws IOException {
        new Test_48().solve();
    }

    private void solve() throws IOException {
        List<String> inputs = Files.readAllLines(input);
        int t = Integer.valueOf(inputs.get(0));
        List<String> results = new ArrayList<>(t);
        for (int i = 1; i <= t; i++) {
            results.add("Case #"+i+": "+solveCase(inputs.get(i)));
        }
        Files.write(output, results);
    }

    private String solveCase(String s) {
        String[] inp = s.split(" ");
        long n = Long.valueOf(inp[0]);
        long k = Long.valueOf(inp[1]);

        TreeMap<Long, Long> segments = new TreeMap<>();
        segments.put(n, 1L);

        long r = n;
        long l = n;

        while (k > 0) {
            long length = segments.lastKey();
            long quantity = segments.pollLastEntry().getValue();

            k -= quantity;

            l = (length - 1) / 2;
            r = (length - 1 - l);

            long ls = segments.getOrDefault(l, 0L);
            segments.put(l, ls + quantity);

            long rs = segments.getOrDefault(r, 0L);
            segments.put(r, rs + quantity);
        }

        return Math.max(l, r) + " " + Math.min(l, r);
    }
}

