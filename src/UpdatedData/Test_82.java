package UpdatedData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krv123
 */
class Stall {
    int x, y;
    public Stall(int a, int b) {
        x = Math.min(a, b);
        y = Math.max(a, b);
    }
}

public class Test_82 {   
    
    public static void main(String args[]) throws Exception {
        PrintStream ps = new PrintStream(new FileOutputStream(new File("src/io/BathroomStall.out")));
        System.setOut(ps);
        Scanner in = new Scanner((new FileReader("src/io/BathroomStall.in")));
        //Scanner in = new Scanner(System.in);
        int T = in.nextInt(); 
        for (int t = 1 ; t <= T ; t++) {
            long N = in.nextLong();
            long K = in.nextLong();
            TreeMap<Long, Long> map = new TreeMap<>(); 
            long min = 0, max = 0;
            if (N/2 != 0) {
                map.put(N/2, 1l);
                max = N/2;
            }
            if ((N-1)/2 != 0) {
                if (N%2 == 1) {
                    map.put(N/2, 2l);
                    min = N/2;
                } else {
                    map.put((N-1)/2, 1l);
                    min = (N-1)/2;
                }
            }
            
            while (K > 1) {
                if (map.isEmpty()) {
                    min = max = 0;
                    break;
                }
                Entry<Long, Long> entry = map.lastEntry();
                K -= entry.getValue();
                max = entry.getKey()/2;
                min = (entry.getKey()-1)/2;
                if (max != 0)
                    if (map.containsKey(max)) map.put(max, map.get(max) + entry.getValue());
                    else map.put(max, entry.getValue());
                if (min != 0)
                    if (map.containsKey(min)) map.put(min, map.get(min) + entry.getValue());
                    else map.put(min, entry.getValue());
                map.pollLastEntry();
            }
            System.out.println("Case #" + t + ": " + max + " " + min);
        }
        in.close();
    }    
    
    public static void mainFailForLarge(String args[]) throws Exception {
//        PrintStream ps = new PrintStream(new FileOutputStream(new File("src/io/BathroomStall.out")));
//        System.setOut(ps);
        Scanner in = new Scanner((new FileReader("src/io/BathroomStall.in")));
        //Scanner in = new Scanner(System.in);
        int T = in.nextInt(); 
        for (int t = 1 ; t <= T ; t++) {
            long N = in.nextLong();
            long K = in.nextLong();
            TreeMap<Long, Long> map = new TreeMap<>(); //(Long i1, Long i2) -> Long.signum(i2 - i1));
            long min = 0, max = 0;
            if (N/2 != 0) {
                map.put(N/2, 1l);
                max = N/2;
            }
            if ((N-1)/2 != 0) {
                if (N%2 == 1) {
                    map.put(N/2, 2l);
                    min = N/2;
                } else {
                    map.put((N-1)/2, 1l);
                    min = (N-1)/2;
                }
            }
            
            while (K-- > 1) {
                if (map.isEmpty()) {
                    min = max = 0;
                    break;
                }
                //Entry<Long, Long> entry = map.firstEntry();
                Entry<Long, Long> entry = map.lastEntry();
                max = entry.getKey()/2;
                min = (entry.getKey()-1)/2;
                if (max != 0)
                    if (map.containsKey(max)) map.put(max, map.get(max) + 1);
                    else map.put(max, 1l);
                if (min != 0)
                    if (map.containsKey(min)) map.put(min, map.get(min) + 1);
                    else map.put(min, 1l);
                if (entry.getValue() == 1) 
                    map.pollLastEntry();
                else
                    map.put(entry.getKey(), entry.getValue() - 1);
            }
            System.out.println("Case #" + t + ": " + max + " " + min);
        }
        in.close();
    }

    public static void mainMedium(String args[]) throws Exception {
//        PrintStream ps = new PrintStream(new FileOutputStream(new File("src/io/BathroomStall.out")));
//        System.setOut(ps);
        Scanner in = new Scanner((new FileReader("src/io/BathroomStall.in")));
        //Scanner in = new Scanner(System.in);
        int T = in.nextInt(); 
        for (int t = 1 ; t <= T ; t++) {
            int N = in.nextInt();
            int K = in.nextInt();
            int len = N*2;
            int[] arr = new int[len];
            arr[0] = N/2; 
            arr[1] = (N-1)/2;
            int idx = 2;
            PriorityQueue<Integer> q = new PriorityQueue<>(N, (Integer i1, Integer i2) -> Integer.signum(i2 - i1));
            if (arr[idx-2] != 0) q.add(arr[idx-2]);
            if (arr[idx-1] != 0) q.add(arr[idx-1]);
            while (idx < len && !q.isEmpty()) {
                int n = q.poll();
                arr[idx++] = n/2;
                arr[idx++] = (n - 1)/2;
                if (arr[idx-2] != 0) q.add(arr[idx-2]);
                if (arr[idx-1] != 0) q.add(arr[idx-1]);
            }
            System.out.println("Case #" + t + ": " + arr[2*K-2] + " " + arr[2*K-1]);
        }
        in.close();
    }

    private static void updateDist(boolean[] brr, Stall[] arr, int idx) {
        int len = 0; int li = idx;
        while (!brr[--li]) ++len;
        for (int i = idx - len, j = 0 ; !brr[i] ; i++) {
            arr[i] = new Stall(j++, Math.abs(--len));
        }
        len = 0; li = idx;
        while (!brr[++li]) ++len;
        for (int i = idx + 1, j = 0 ; !brr[i] ; i++) {
            arr[i] = new Stall(j++, Math.abs(--len));
        }
    }
    
    public static void mainSmall(String args[]) throws Exception {
//        PrintStream ps = new PrintStream(new FileOutputStream(new File("src/io/BathroomStall.out")));
//        System.setOut(ps);
        Scanner in = new Scanner((new FileReader("src/io/BathroomStall.in")));
        //Scanner in = new Scanner(System.in);
        int T = in.nextInt(); 
        for (int t = 1 ; t <= T ; t++) {
            int N = in.nextInt();
            int K = in.nextInt();
            
            Stall[] arr = new Stall[N+2];
            for (int i = 1 ; i <= N ; i++) {
                arr[i] = new Stall(i - 1, Math.abs(N - i));
            }
            boolean[] brr = new boolean[N+2];
            brr[0] = true;
            brr[N+1] = true;
                        
            int minD = 0;
            int idx = -1;
            
            for (int k = 0 ; k < K ; k++) {
                minD = Integer.MIN_VALUE;
                List<Integer> xList = new ArrayList<>();
                for (int i = 1 ; i <= N ; i++) {
                    if (!brr[i]) {
                        if (arr[i].x > minD) {
                            minD = arr[i].x;
                            xList.clear();
                            xList.add(i);
                        } else if (arr[i].x == minD) {
                            xList.add(i);
                        }
                    }
                }                
                
                idx = xList.get(0);
                if (xList.size() > 1) {                    
                    for (int p = 1 ; p < xList.size() ; p++) {
                        if (arr[xList.get(p)].y > arr[idx].y) {
                            idx = xList.get(p);
                            break;
                        }
                    }
                }
                brr[idx] = true;
                updateDist(brr, arr, idx);
                //System.out.println(Arrays.toString(brr));
            }
            
            System.out.println("Case #" + t + ": " + arr[idx].y + " " + arr[idx].x);
        }
        in.close();
    }
}

