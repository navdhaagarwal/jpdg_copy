import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * 
 * Comments Here
 * 
 */
public class C
{
	static BufferedReader br; 
	static BufferedWriter bw; 
	static StringTokenizer st;
	
	static int caseNum;
	static long people;
	static long bestSeg;
	
	static PriorityQueue<Long> segs;
	static HashMap<Long,Long> segCount;

	public static void main(String[] args) throws java.lang.Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	//Uncomment to read from file
    	/*/
    	File file = new File("src/in.txt");
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/**/
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; ++i) {
			segs = new PriorityQueue<Long>();
			segCount = new HashMap<Long,Long>();
			caseNum = i+1;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			long stalls = Long.parseLong(st.nextToken());
			people = Long.parseLong(st.nextToken());
			
			segs.add(-stalls);
			segCount.put(stalls, 1L);
			
			solve();
		}
		br.close();
		bw.close();
	}
	
	
	public static void print(long segment) {
		long l = (segment-1)/2;
		long r = segment/2;
		System.out.println("Case #" + caseNum + ": " + r + " " + l);
	}
	
	
	public static void solve(){
		
		while(people > 0) {
			bestSeg = -segs.poll();
			long bestSegCount = segCount.get(bestSeg);
			segCount.remove(bestSeg);
			
			long l = (bestSeg-1)/2;
			long r = bestSeg/2;
			
			if(segCount.containsKey(l)) {
				segCount.put(l, segCount.get(l)+bestSegCount);
			} else {
				segCount.put(l, bestSegCount);
				segs.add(-l);
			}
			
			if(segCount.containsKey(r)) {
				segCount.put(r, segCount.get(r)+bestSegCount);
			} else {
				segCount.put(r, bestSegCount);
				segs.add(-r);
			}
			people -= bestSegCount;
		}
		
		print(bestSeg);
		
	}
	
	
	
}

