package com.matlabheaderhtml.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



public class Writer {

	/**
	 * write a matlab function in a string buffer
	 * @param fun the matlab function to write
	 * @param sb the string buffer where to write
	 * @param pathToDoc the path to documentation (needed for correct html link)
	 */
	public static void write(MatlabFunction fun, StringBuffer sb, String pathToDoc){

		sb.append("<h1> \n");
		sb.append("Function "+fun.getName().toUpperCase());
		sb.append("</h1>\n");

		Writer.writeSubtitle("Package", sb);
		if (fun.getMatlabPackage() != null){
			sb.append("<a href=\"" +pathForPackage(fun.getMatlabPackage(), pathToDoc)+"\"> " );
			sb.append(fun.getPackageName());
			sb.append("</a>");
		} else {
			sb.append(fun.getPackageName());
		}

		Writer.writeSubtitle("Short description", sb);
		sb.append(fun.getDescriptionOneLiner());

		Writer.writeSubtitle("Usage", sb);
		writeCode(fun.getUsage(), sb);

		Writer.writeSubtitle("Input", sb);
		writeCode(fun.getInput(), sb);

		Writer.writeSubtitle("Output", sb);
		writeCode(fun.getOuput(), sb);

		Writer.writeSubtitle("Description", sb);
		writeCode(fun.getDescription(), sb);

		Writer.writeSubtitle("See also", sb);
		sb.append("<ul>");

		for (MatlabFunction sibling : fun.getSeeAlso()){
			String pathToSibling = pathForFun(sibling, pathToDoc);
			sb.append("<li>\n <a href=\""+pathToSibling+" \"> " + sibling.getName() +"</a></li>");
		}
		sb.append("</ul>\n\n");
		sb.append(linkToAllPackages(pathToDoc));
	}

	/**
	 * utility method to write between h2 html tag 
	 * @param subtitle
	 * @param sb
	 */
	private static void writeSubtitle(String subtitle, StringBuffer sb){
		sb.append("<h2> \n");
		sb.append(subtitle);
		sb.append("\n </h2> \n");
	}

	/**
	 * utility method to write between pre and code html tag 
	 * @param subtitle
	 * @param sb
	 */
	private static void writeCode(String str, StringBuffer sb){
		sb.append("<pre><code>");
		sb.append(str);
		sb.append("</code></pre>");
	}

	/**
	 * write a matlab function in a file 
	 * @param fun the matlab function to write
	 * @param pathToDoc the path to documentation directory
	 */
	public static void writeFunInDoc(MatlabFunction fun, String pathToDoc){
		try {
			//MatlabFunction fun = Parser.read(Parser.readFileAsString(pathToFile));
			StringBuffer sb = new StringBuffer();
			Writer.write(fun, sb, pathToDoc);
			String str = sb.toString();
			String pathForThisDocFile = pathForFun(fun, pathToDoc);
			System.out.println(pathForThisDocFile);
			PrintWriter writer = new PrintWriter(pathForThisDocFile);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a utility method to compute the path of file where to write matlab function
	 * @param fun
	 * @param pathToDoc
	 * @return
	 */
	public static String pathForFun(MatlabFunction fun, String pathToDoc){
		return pathToDoc + "/" + fun.getPackageName() + "/" + fun.getName() + ".html";
	}


	private static String toString(MatlabPackage matlabPackage, String pathToDoc){
		StringBuffer sb = new StringBuffer();
		sb.append("<h1> \n");
		sb.append("Package "+matlabPackage.getName());
		sb.append("</h1> \n");
		sb.append("<ul>");
		for (MatlabFunction sibling : matlabPackage.getFunctions()){
			String pathToSibling = pathForFun(sibling, pathToDoc);
			sb.append("<li>\n <a href=\""+pathToSibling+" \"> " + sibling.getName() +"</a></li>");
		}
		sb.append("</ul>\n\n");
		sb.append(linkToAllPackages(pathToDoc));
		return sb.toString();
	}

	public static void writePackageInDoc(MatlabPackage matlabPackage, String pathToDoc){
		try {
			String str = toString(matlabPackage, pathToDoc);
			String pathForThisDocFile = pathForPackage(matlabPackage, pathToDoc);
			System.out.println(pathForThisDocFile);
			PrintWriter writer = new PrintWriter(pathForThisDocFile);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String pathForPackage(MatlabPackage matlabPackage, String pathToDoc){
		return pathToDoc + "/" + matlabPackage.getName() + "/all_functions.html";
	}

	private static String toString(List<MatlabPackage> listOfPackages, String pathToDoc){
		StringBuffer sb = new StringBuffer();
		sb.append("<h1> \n");
		sb.append("All packages");
		sb.append("</h1>\n");
		sb.append("<ul>");
		for (MatlabPackage matlabPackage : listOfPackages){
			sb.append("<li>");
			sb.append("<a href=\"" + pathForPackage(matlabPackage, pathToDoc)+"\">" + matlabPackage.getName() + "</a>");
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}
	
	public static void writeListOfPackageInDoc(List<MatlabPackage> listOfPackages, String pathToDoc){
		try {
			String str = toString(listOfPackages, pathToDoc);
			String pathForThisDocFile = pathToDoc + "/all_packages.html";
			System.out.println(pathForThisDocFile);
			PrintWriter writer = new PrintWriter(pathForThisDocFile);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private static String linkToAllPackages(String pathToDoc){
		return "<a href=\"" + pathToDoc + "/all_packages.html" +"\">" + "List of all packages" + "</a>";
	}
}
