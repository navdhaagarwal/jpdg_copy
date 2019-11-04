/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codejam2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HoangSon
 */
public class task3 {

    private static final String input = "test3.txt";
    private static final String output = "output3.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader r = new BufferedReader(new FileReader(input));
            BufferedWriter w = new BufferedWriter(new FileWriter(output));

            String line = r.readLine();
            int testcaseNum = Integer.valueOf(line);

            for (int i = 0; i < testcaseNum; i++) {
                Map<Long, Long> result = new HashMap<Long, Long>();
                line = r.readLine();
//                System.out.println("line: " + line);
                long N = Long.valueOf(line.split(" ")[0]);
                long K = Long.valueOf(line.split(" ")[1]);
//                long N = 25;
//                long K = 3;

                result.put(N, 1l);

                int loopCount = 0;
                Long K1 = 0l;
                Long K2 = 0l;
                long numUsers = 0;
                while (true) {
                    numUsers = (long) Math.pow(2, loopCount);
                    System.out.println("K = " + K + "  numbers " + numUsers);
                    print(result);
                    if (K > numUsers) {
                        HashMap<Long, Long> temp = new HashMap<>();
                        for (Map.Entry<Long, Long> entry : result.entrySet()) {
                            temp.put(entry.getKey(), entry.getValue());
                        }

                        for (Long key : temp.keySet()) {
                            Long freq = result.get(key);
                            K1 = (key - 1) / 2 < 0 ? 0 : (key - 1) / 2;
                            K2 = key - K1 - 1 < 0 ? 0 : key - K1 - 1;
                            result.remove(key);
                            putToMap(result, K1, freq);
                            putToMap(result, K2, freq);
                        }
                    } else {
                        long maxKey = getMaxkey(result);
                        if (K <= result.get(maxKey)) {
                            K1 = (maxKey - 1) / 2 < 0 ? 0 : (maxKey - 1) / 2;
                            K2 = maxKey - K1 - 1 < 0 ? 0 : maxKey - K1 - 1;

                        } else {
                            K1 = (maxKey - 2) / 2 < 0 ? 0 : (maxKey - 2) / 2;
                            K2 = maxKey - K1 - 2 < 0 ? 0 : maxKey - K1 - 2;
                        }
                        w.write("Case #" + (i + 1) + ": " + K2 + " " + K1 + "\n");
                        System.out.println("result = " + "Case #" + (i + 1) + ": " + K2 + " " + K1);
                        break;
                    }
                    K -= numUsers;
                    loopCount++;
                }
            }
            w.flush();

            w.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(task4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(task4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static long getGroupNumber(Map<Long, Long> result) {
        long counter = 0l;
        for (Long key : result.keySet()) {
            counter += result.get(key);
        }
        return counter;
    }

    private static void putToMap(Map<Long, Long> result, Long K, long freq) {
        if (result.containsKey(K)) {
            Long value = result.get(K);
            result.remove(K);
            result.put(K, value + freq);
        } else {
            result.put(K, freq);
        }
    }

    private static void print(Map<Long, Long> result) {
        for (Long key : result.keySet()) {
            System.out.print("key: " + key + "-> " + result.get(key) + " && ");
        }
        System.out.println("");
    }

    private static Long getMaxkey(Map<Long, Long> result) {
        Long maxKey = -1l;
        for (Long key : result.keySet()) {
            maxKey = key > maxKey ? key : maxKey;
        }
        return maxKey;
    }

}
