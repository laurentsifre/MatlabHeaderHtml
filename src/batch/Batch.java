package batch;

import hierarchy.MatlabFunction;
import html.Writer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parse.Parser;

public class Batch {

	public static void parseAll(String pathToFile, List<String> validPackages){
		String pathToDoc = "/Users/laurentsifre/Dropbox/these/code/docscatnet";
		for (String currPackage : validPackages){
			File theDir = new File(pathToDoc +"/"+ currPackage);
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				System.out.println("creating directory: " + currPackage);
				boolean result = theDir.mkdir();  
				if(result) {    
					System.out.println("DIR created");  
				}
			}
			String pathToPackage = pathToFile + "/" + currPackage;
			List<String> allFiles = Batch.listOfFileInFolderWithExtension(pathToPackage, ".m");
			// first pass to parse all
			for (String file : allFiles){
				try {
					System.out.println(file);
					MatlabFunction fun = Parser.readMatlabFunFromFile(pathToPackage+"/"+file);
					fun.setPackageName(currPackage);
					Writer.writeFunInDoc(fun, pathToDoc);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// second pass with all the link
			for (String file : allFiles){
				try {
					System.out.println(file);
					MatlabFunction fun = Parser.readMatlabFunFromFile(pathToPackage+"/"+file);
					fun.setPackageName(currPackage);
					Writer.writeFunInDoc(fun, pathToDoc);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(allFiles);
		}
	}

	public static void main(String[] args){
		List<String> validPackages = Arrays.asList("core", "convolution");
		parseAll("/Users/laurentsifre/Dropbox/these/code/scatnet/scatnet", validPackages);
	}

	public static List<String> listOfFileInFolderWithExtension(String folder, final String extension){

		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return (name.endsWith(extension));
			}
		};

		File dir = new File(folder);

		List<String> allFiles = new ArrayList<String>();
		if(dir.isDirectory()==false){
			System.out.println("Directory does not exists : " + folder);
			return allFiles;
		}

		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);

		if (list.length == 0) {
			System.out.println("no files end with : " + extension);
			return allFiles;
		}

		for (String file : list) {
			allFiles.add(file);
		}
		return allFiles;
	}
}
