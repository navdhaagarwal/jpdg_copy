package UpdatedData;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Test_18
{
	static LinkedHashMap<Long, Long> map;
	
	static void initMap(long n){
		if( !map.containsKey(n) ){
			map.put(n, 0L);
			if( n>=1 ){
				initMap((n-1)/2);
				initMap((n-1) - (n-1)/2);
			}
		}
	}

	static public void solveOne(int caseNo, Scanner in, PrintStream out)
	{
		long N = in.nextLong();
		long K = in.nextLong();
		in.nextLine();

		map = new LinkedHashMap<>();
		initMap(N);
		
		Long[] keys = map.keySet().toArray(new Long[0]);
		Arrays.sort(keys);
		
		//System.out.println(Arrays.toString(keys));
		
		map.put(N, 1L);
		
		int k = keys.length-1;
		long maxlr = N, minlr = N;
		while( K>0 ){
			long size = keys[k];
			long ns = Math.min(map.get(size), K);
			if( ns==0 ){
				k--;
			} else {
				K -= ns;
				minlr = (size-1)/2;
				maxlr = (size-1) - minlr;
				map.put( size, map.get(size)-ns );
				map.put( minlr, map.get(minlr)+ns );
				map.put( maxlr, map.get(maxlr)+ns );
			}
		}
		
		String answer = maxlr + " " + minlr;

		out.println("Case #" + caseNo + ": " + answer);
	}
	
	static public void solveAll(String infilename, String outfilename)
	{
		try {
			FileInputStream stream = new FileInputStream(infilename);
			Scanner in = new Scanner(stream);
			
			PrintStream out = null;
			if( outfilename!=null ){
				out = new TeeStdout(outfilename);
				System.out.println("--- " + outfilename);
			} else {
				out = System.out;
			}

			// loop on cases
			int nbcases = in.nextInt();
			in.nextLine();
			for( int n=1 ; n<=nbcases ; n++ ){
				solveOne(n, in, out);
			}
			
			out.flush();
			out.close();
		} catch( IOException ex ){
			ex.printStackTrace();
		}
		
	}

	public static class TeeStdout extends PrintStream {
		public TeeStdout(String filename) throws FileNotFoundException {
			super(new PrintStream(new BufferedOutputStream(new FileOutputStream(filename))));
		}

		@Override
		public void write(byte[] buf, int off, int len) {
			super.write(buf, off, len);
			System.out.write(buf, off, len);
		}
	}

	public static void main(String[] args)
	{
		String problem;
		if( args.length>0 ){
			problem = args[0];
		} else {
			String cname = C.class.getSimpleName();
//			problem = cname + "-sample";
//			problem = cname + "-small-2-attempt0";
//			problem = cname + "-small-attempt1";
			problem = cname + "-large";
		}
		String dir = "src/" + C.class.getPackage().getName().replaceAll("\\.", "/") + "/";
		solveAll(dir + problem + ".in", dir + problem + ".out");
	}
}

