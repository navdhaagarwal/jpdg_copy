package codeJam2017;

import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class RQP3 {
	String line;
	StringTokenizer inputParser;
	BufferedReader is;
	FileInputStream fstream;
	DataInputStream in;
	
	void openInput(String file)
	{

		//is = new BufferedReader(new InputStreamReader(System.in));//stdin
		try{
			fstream = new FileInputStream(file);
			in = new DataInputStream(fstream);
			is = new BufferedReader(new InputStreamReader(in));
		}catch(Exception e)
		{
			System.err.println(e);
		}

	}
	
	void readNextLine()
	{
		try {
			line = is.readLine();
			inputParser = new StringTokenizer(line, " ");
			//System.err.println("Input: " + line);
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}	
		
	}
	
	int NextInt()
	{
		String n = inputParser.nextToken();
		int val = Integer.parseInt(n);
		
		//System.out.println("I read this number: " + val);
		return val;
	}
	
	long NextLong()
	{
		String n = inputParser.nextToken();
		long val = Long.parseLong(n);
		
		//System.out.println("I read this number: " + val);
		return val;
	}
	
	String NextString()
	{
		String n = inputParser.nextToken();
		return n;
	}
	
	void closeInput()
	{
		try {
			is.close();
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
			
	}
	
	public static void main(String [] argv)
	{
		new RQP3(argv[0]);
	}
	
	public RQP3(String inputFile)
	{
		openInput(inputFile);
		readNextLine();

		int TC = NextInt();

		
		for(int tt=0; tt<TC; tt++)
		{	
			String output=solve();
			System.out.println("Case #"+(tt+1)+": "+output);
		}
		closeInput();
	}
	
	private String solve() {
		readNextLine();
		long N = NextLong();
		long K = NextLong() - 1;
		
		PriorityQueue<Long>q = new PriorityQueue<Long>(Collections.reverseOrder());
		HashMap<Long, Long>s = new HashMap<Long, Long>();
		q.add(N);
		s.put(N, 1L);
		
		while(K>0)
		{
			
			long x = q.poll();
			long c = s.get(x);
			if(K<c)
			{
				q.add(x);
				break;
			}
			K-=c;
			//System.out.println(x.max);
			if(x%2==1)
			{
				if(s.containsKey(x/2))s.put(x/2, s.get(x/2)+2*c);
				else
				{
					s.put(x/2, 2*c);
					q.add(x/2);
				}
			}else
			{
				if(s.containsKey(x/2))s.put(x/2, s.get(x/2)+c);
				else
				{
					s.put(x/2, c);
					q.add(x/2);
				}
				if(s.containsKey(x/2-1))s.put(x/2-1, s.get(x/2)+c);
				else
				{
					s.put(x/2-1, c);
					q.add(x/2-1);
				}
			}
		}
		long x = q.peek();
		if(x%2==1)
			return x/2 + " " + x/2;
		return x/2 + " " + (x/2 - 1);
	}


}


