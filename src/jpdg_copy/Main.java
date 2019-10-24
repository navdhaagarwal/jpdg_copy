package jpdg_copy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import jpdg_copy.JPDG;

public class Main {

	public static void main(String args[]) throws IOException {
		
		Path path = Paths.get(System.getProperty("user.dir"));
		Path test = Paths.get(path.toString(), "src", "test");
		File files[] = new File(test.toString()).listFiles();
		for (File file : files)
		{
			String fileName = file.getName();
			fileName = fileName.substring(0,fileName.lastIndexOf('.'));
			String[] file_arr = new String[] {"test." + fileName}; 
			JPDG.test(file_arr, fileName);
		}
	
	}

}
