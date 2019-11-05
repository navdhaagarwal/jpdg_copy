package UpdatedData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Test_17 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("C.in")));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("C.out"))));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String[] str = br.readLine().split(" ");
			long N = Long.parseLong(str[0]);
			long K = Long.parseLong(str[1]);
			pw.println("Case #"+(t+1)+": "+analyze(N, K));
		}

		pw.close();
		br.close();
	}
	
	private static String analyze(long N, long K) {
		TreeMap<Long,Long> tm = new TreeMap<Long,Long>();
		tm.put(N, 1L);
		long c = 0, k = 0, v = 0;
		while(c < K) {
			k = tm.lastKey();
			v = tm.get(k);
			tm.remove(k);
			c += v;
			if (k%2 == 0) {
				if (tm.containsKey(k/2)) tm.put(k/2, tm.get(k/2)+v);
				else tm.put(k/2, v);
				if (tm.containsKey((k-1)/2)) tm.put((k-1)/2, tm.get((k-1)/2)+v);
				else tm.put((k-1)/2, v);
			} else {
				if (tm.containsKey((k-1)/2)) tm.put((k-1)/2, tm.get((k-1)/2)+v*2);
				else tm.put((k-1)/2, v*2);
			}
		}
		return ""+(k/2)+" "+((k-1)/2);
	}
}

