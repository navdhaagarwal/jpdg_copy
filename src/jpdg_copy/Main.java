package jpdg_copy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import jpdg_copy.JPDG;

public class Main {
	
//public static void main(String args[]) throws IOException {
//		
//		Path path = Paths.get(System.getProperty("user.dir"));
//		Path test = Paths.get(path.toString(), "ProgramData");
//		File folders[] = new File(test.toString()).listFiles();
//		for (File folder : folders)
//		{
//			Path files = Paths.get(test.toString(),folder.getName());
//			File Files[] = new File(files.toString()).listFiles();
//			System.out.println(Files[0].getName());
//			for(File file: Files)
//			{
//				Path file_path = Paths.get(files.toString(), file.getName());
//				String fileName = file.getName();
//				fileName = fileName.substring(0,fileName.lastIndexOf('.'));
//				String f = file_path.toString();
//				f = f.substring(0,f.lastIndexOf('.'));
//				String[] file_arr = new String[] {f.toString()}; 
//				System.out.println(f);
//				JPDG.test(file_arr, fileName);
//				break;
//			}
//			break;
//			
//		}
	
//	}s

	public static void main(String args[]) throws IOException {
		
		Path path = Paths.get(System.getProperty("user.dir"));
		Path test = Paths.get(path.toString(), "src");
		File files[] = new File(test.toString()).listFiles();
		for (File file : files)
		{
			if(file.getName().contains(".java"))
			{
				System.out.println(file.getName());
				String fileName = file.getName();
				fileName = fileName.substring(0,fileName.lastIndexOf('.'));
				String[] file_arr = new String[] {fileName}; 
				JPDG.test(file_arr, fileName);
			}
			
		}
	
	}

}
