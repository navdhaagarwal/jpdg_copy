package UpdatedData;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test_36 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			long N = in.nextLong();
			long K = in.nextLong();
			Comparator<Long> rev = (o1, o2) -> Long.compare(o2, o1);
			TreeMap<Long, Long> tm = new TreeMap<Long, Long>(rev);
			tm.put(N, 1l);
			long count = 0;
			while (count < K) {
				//System.out.println(tm);
				Map.Entry<Long, Long> a = tm.firstEntry();
				long val = a.getKey();
				tm.remove(val);
				long num = a.getValue();
				count+=num;
				long left = val/2;
				long right = (val-1)/2;
				if(count>=K){
					System.out.println("Case #"+t+": "+left+" "+right);
					break;
				}
				//System.out.println(count+" "+num+" "+left + " " + right);
				Long leftval = tm.get(left);
				long leftnew = num;
				if(leftval!=null)leftnew+=leftval;
				tm.put(left, leftnew);
				
				long rightnew = num;
				Long rightval = tm.get(right);
				if(rightval!=null)rightnew+=rightval;
				tm.put(right, rightnew);
			}
		}
	}

}

