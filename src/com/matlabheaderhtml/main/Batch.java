package com.matlabheaderhtml.main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Batch {

	public static void 	parseAll(String pathToYourCode, String pathToOutputDoc, List<String> validPackages){
		// convert to absolute paths
		File fileToYourCode = new File(pathToYourCode);
		File fileToOutputDoc = new File(pathToOutputDoc);
		try {
			pathToYourCode = fileToYourCode.getCanonicalPath();
			pathToOutputDoc = fileToOutputDoc.getCanonicalPath();
			System.out.println("absolute path to your code is "+pathToYourCode);
			System.out.println("absolute path to your doc is "+pathToOutputDoc);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// first pass to parse all
		for (String currPackage : validPackages){
			File theDir = new File(pathToOutputDoc +"/"+ currPackage);
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				System.out.println("creating directory: " + currPackage);
				boolean result = theDir.mkdir();  
				if(result) {    
					System.out.println("DIR created");  
				}
			}
			String pathToPackage = pathToYourCode + "/" + currPackage;
			List<String> allFiles = Batch.listOfFileInFolderWithExtension(pathToPackage, ".m");
			for (String file : allFiles){
				try {
					System.out.println("first pass on "+file);
					MatlabFunction fun = Parser.readMatlabFunFromFile(pathToPackage+"/"+file);
					fun.setPackageName(currPackage);
					//Writer.writeFunInDoc(fun, pathToOutputDoc);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// second pass with all the link
		for (String currPackage : validPackages){
			String pathToPackage = pathToYourCode + "/" + currPackage;
			List<String> allFiles = Batch.listOfFileInFolderWithExtension(pathToPackage, ".m");
			for (String file : allFiles){
				try {
					System.out.println("second pass on "+file);
					String filePath = pathToPackage+"/"+file;
					MatlabFunction fun = Parser.readMatlabFunFromFile(filePath);
					fun.setPackageName(currPackage);
					Writer.writeFunInDoc(fun, pathToOutputDoc);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(allFiles);
		}
	}

	public static void main(String[] args) throws IOException{
		if (args.length < 2){
			BufferedReader br = new BufferedReader(new FileReader("readme.md"));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
		}
		else {
			String pathToYourCode = args[0];
			String pathToOutputDoc = args[1];
			
			List<String> validPackages = Arrays.asList(args).subList(2, args.length);
			parseAll(pathToYourCode, pathToOutputDoc, validPackages);
		}
	}

	public static List<String> listOfFileInFolderWithExtension(String folder, final String extension){

		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
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
