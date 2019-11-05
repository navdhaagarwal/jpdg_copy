package UpdatedData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by huanglei on 2017/4/8.
 */
public class Test_39 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        in.nextLine();
        for (int i = 1; i <= t; ++i) {
            String line = in.nextLine();
            Long N = Long.parseLong(line.split(" ")[0]);
            Long K = Long.parseLong(line.split(" ")[1]);

            Set<Long> positions = new HashSet<>();
            positions.add(N);
            Map<Long,Long> pmaps = new HashMap<>();
            pmaps.put(N,new Long(1));
            Long max = new Long(0);
            while (K>0){
                max = Collections.max(positions);
                Long times = pmaps.get(max);
                K-=times;
                positions.remove(max);
                positions.add((max-1)/2);
                positions.add(max/2);
                if(pmaps.containsKey((max-1)/2))
                    pmaps.put((max-1)/2,pmaps.get((max-1)/2)+times);
                else{
                    pmaps.put((max-1)/2,new Long(times));
                }
                if(pmaps.containsKey((max)/2))
                    pmaps.put((max)/2,pmaps.get((max)/2)+times);
                else{
                    pmaps.put((max)/2,new Long(times));
                }
            }
            Long output1 = max/2;
            Long output2 = (max-1)/2;
            System.out.println("Case #" + i + ": "+ output1+" "+output2);

        }
    }
}

