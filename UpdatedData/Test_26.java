package UpdatedData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Author : vttgato
//Email  : votoanthuan@gmail.com
//JDK    : Java SE 8 (Oracle)

public class Test_26
{
    private final static ExecutorService executor = Executors.newWorkStealingPool();
    private final static char NEWLINE   = '\n';

    private static ConcurrentHashMap<Integer, String> mapResult;
    private static CountDownLatch latch;

    public static abstract class Task implements Runnable
    {
        protected int testcase;
        protected StringBuilder log = new StringBuilder();

        private Task (int testcase)
        {
            this.testcase = testcase;
        }

        protected void addResult (String result)
        {
            mapResult.put(testcase, result);
        }

        public abstract void handle () throws Exception;

        @Override
        public void run ()
        {
            try
            {
                handle();
            }
            catch (Exception e)
            {
                console("Exception in testcase", testcase, e.getMessage());
                e.printStackTrace();
            }
            finally
            {
                console(log);
                latch.countDown();
            }
        }

        public void print (Object... ao)
        {
            for (Object o : ao)
                log.append(o).append(' ');
        }

        public void println (Object... ao)
        {
            print(ao);
            log.append(NEWLINE);
        }
    }

    public static void main (String[] args)
    {
        inputFile = "./" + inputFile;
        final String outputFile = inputFile.replace(".in", ".out");
        long startTime = System.currentTimeMillis();
        int numTestCase = 0;

        try (BufferedReader input = Files.newBufferedReader(Paths.get(inputFile)))
        {
            Scanner scanner = new Scanner(input);
            numTestCase = scanner.nextInt();
            scanner.nextLine();
            console("numTestCase", numTestCase);

            latch = new CountDownLatch(numTestCase);
            mapResult = new ConcurrentHashMap<>(numTestCase);
            for (int t = 1; t <= numTestCase; t++)
                executor.submit(new DemoTask(t, scanner));
            latch.await();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        console();
        console("Total time:", (System.currentTimeMillis() - startTime) / 1000);
        console();

        if (mapResult != null)
        {
            try (BufferedWriter output = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))
            {
                for (int t = 1; t <= numTestCase; t++)
                {
                    String r = mapResult.get(t);
                    if (r == null)
                        continue;

                    String c = join("Case #", t, ": ", r);
                    output.write(c);
                    output.newLine();

                    console(c);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static String join (Object... ao)
    {
        StringBuilder sb = new StringBuilder();
        for (Object o : ao)
            sb.append(o);
        return sb.toString();
    }

    public static void console ()
    {
        System.out.println();
    }

    public static void console (Object... ao)
    {
        StringBuilder sb = new StringBuilder();
        for (Object o : ao)
            sb.append(o).append(' ');
        System.out.println(sb);
    }

//    private static String inputFile = "data/C-pratice.in";
    //private static String inputFile = "data/C-small-2-pratice.in";
    private static String inputFile = "data/C-small-2-attempt0.in";

    public static class DemoTask extends Task
    {
        long k, n;
        ArrayList<Long> blocks;

        public DemoTask (int testcase, Scanner scanner)
        {
            super(testcase);
            n = scanner.nextLong();
            k = scanner.nextLong();
            scanner.nextLine();

            blocks = new ArrayList<>();
            blocks.add(n);
        }

        @Override
        public void handle () throws Exception
        {
            println("testcase", testcase, "n", n, "k", k);

            /*ArrayList<Long> q = new ArrayList<>();
            q.add(n+1);
            long tmp = 0;

            while(k > 0)
            {
                k--;
                tmp = q.remove(q.size() - 1);
                if((tmp%2) == 1)
                {
                    q.add((tmp>>1)+1);
                    q.add(tmp>>1);
                }
                else
                {
                    q.add(tmp>>1);
                    q.add(tmp>>1);
                }
                //Collections.sort(q, Collections.reverseOrder());
                //Collections.sort(q);
            }

            String s;
            if ((tmp%2) == 1)
                s = (tmp>>1) + " " + ((tmp>>1)-1);
            else
                s = (tmp>>1) + " " + (tmp>>1);*/

            /*ArrayDeque<Long> q = new ArrayDeque<>();
            q.push(n+1);
            long tmp = 0;

            while(k > 0)
            {
                k--;
                tmp = q.pollFirst();
                if((tmp%2) == 1)
                {
                    q.push((tmp>>1)+1);
                    q.push(tmp>>1);
                }
                else
                {
                    q.push(tmp>>1);
                    q.push(tmp>>1);
                }
            }

            String s;
            if ((tmp%2) == 1)
                s = (tmp>>1) + " " + ((tmp>>1)-1);
            else
               s = (tmp>>1) + " " + (tmp>>1);*/

            /*String s;
            long left = n - k;
            long numDiv = k + 1;
            long d;

            if (n == k)
                s = "0 0";
            else if (left < numDiv)
                s = "1 0";
            else
            {
                println("left", left, "numDiv", numDiv);
                d = left / numDiv;
                if ((left % numDiv) == 0)
                    s = d + " c " + d;
                else
                    s = d + " d " + (d - 1);
            }*/

            double left = 0, right = 0;
            if (k < n)
            {
                int b = 1;
                double a = Math.pow(2, b);
                while (a <= k)
                {
                    b++;
                    a = Math.pow(2, b);
                }

                double div = Math.floor(((double) (n - k) / (Math.pow(2, b - 1))) + 1);

                left = div / 2;
                right = left;
                if (div % 2 == 0)
                    right--;
            }

            String s = (long) Math.max(left, right) + " " + (long) Math.min(left, right);


            /*long[] r = new long[2];
            long block, div;
            for (long i = k; i > 0; i--)
            {
                block = blocks.remove(0);
                div = block / 2;

                if ((block % 2) == 0)
                {
                    blocks.add(div);
                    blocks.add(div - 1);
                    r[0] = div;
                    r[1] = div - 1;
                }
                else
                {
                    blocks.add(div);
                    blocks.add(div);
                    r[0] = div;
                    r[1] = div;
                }
                Collections.sort(blocks, Collections.reverseOrder());
            }

            //println(blocks);
            String s = r[0] + " " + r[1];*/



            addResult(s);
        }
    }


}

