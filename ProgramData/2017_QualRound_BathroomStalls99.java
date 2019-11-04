import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Pair {
    public long val;
    public long count;
    public Pair(long v, long c) {
        val = v;
        count = c;
    }
}

public class c {
    static String in = "src/c.in";
    static String out = "src/c.out";
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File(in));
        BufferedWriter write = new BufferedWriter(new FileWriter(out));
        int numlines = scan.nextInt();
        for(int line = 1; line <= numlines; line++) {
            long numStalls = scan.nextLong();
            long numPeople = scan.nextLong();
            
            long left = (numStalls+1)/2;
            long right = (numStalls+1)/2;
            if(numStalls % 2 == 0) {
                left++;
            }
            
            if(numPeople == 1) {
                write.write("Case #" + line + ": ");
                write.write((left-1) + " " + (right-1));
                write.write("\n");
                
                continue;
            }
            
            ArrayList<Pair> data = new ArrayList<Pair>();
            if(left == right) {
                data.add(new Pair(left, 2));
            } else {
                data.add(new Pair(left, 1));
                data.add(new Pair(right, 1));
            }
            
            long people = 1;
            while(true) {
                long nextRange = data.get(0).val;
                long nextGroupSize = data.get(0).count;
                
                //System.err.println(people + ": " + nextRange + ", " + nextGroupSize);
                
                long nextLeft = nextRange/2;
                long nextRight = nextRange/2;
                if(nextRange % 2 == 1) {
                    nextLeft++;
                }
                
                people += nextGroupSize;
                
                if(people >= numPeople) {
                    write.write("Case #" + line + ": ");
                    write.write((nextLeft-1) + " " + (nextRight-1));
                    write.write("\n");
                    break;
                }
                
                for(int x = 1; x < data.size(); x++) {
                    if(data.get(x).val == nextLeft) {
                        data.get(x).count += nextGroupSize;
                        nextLeft = 0;
                        break;
                    } else if(data.get(x).val < nextLeft) {
                        Pair temp = new Pair(nextLeft, nextGroupSize);
                        data.add(x - 1, temp);
                        nextLeft = 0;
                        break;
                    }
                }
                if(nextLeft > 0) {
                    Pair temp = new Pair(nextLeft, nextGroupSize);
                    data.add(temp);
                }
                
                for(int x = 1; x < data.size(); x++) {
                    if(data.get(x).val == nextRight) {
                        data.get(x).count += nextGroupSize;
                        nextRight = 0;
                        break;
                    } else if(data.get(x).val < nextRight) {
                        Pair temp = new Pair(nextRight, nextGroupSize);
                        data.add(x - 1, temp);
                        nextRight = 0;
                        break;
                    }
                }
                if(nextRight > 0) {
                    Pair temp = new Pair(nextRight, nextGroupSize);
                    data.add(temp);
                }
                data.remove(0);
            }
        }
        scan.close();
        write.close();
    }
}
