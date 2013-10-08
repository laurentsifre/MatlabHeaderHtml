package com.matlabheaderhtml.main;

import java.io.IOException;
import java.io.PrintWriter;



public class Writer {

	public static void write(MatlabFunction fun, StringBuffer sb, String pathToDoc){
		
		sb.append("<h1> \n");
		sb.append("<a href=\"" +pathForFun(fun, pathToDoc)+"\"> " );
		sb.append(fun.getName());
		sb.append("</a>");
		sb.append("</h1>\n");
		
		Writer.writeSubtitle("Package", sb);
		sb.append(fun.getPackageName());
		
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
	}
	
	private static void writeSubtitle(String subtitle, StringBuffer sb){
		sb.append("<h2> \n");
		sb.append(subtitle);
		sb.append("\n </h2> \n");
	}
	
	private static void writeCode(String str, StringBuffer sb){
		sb.append("<pre><code>");
		sb.append(str);
		sb.append("</code></pre>");
	}
	
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
	
	public static void main(String[] args){
		String pathToDoc = "/Users/laurentsifre/Dropbox/these/code/docscatnet";
		String pathToFile = "/Users/laurentsifre/Dropbox/these/code/scatnet/scatnet/core/scat.m";
		try {
			MatlabFunction fun = Parser.readLinePerLine(Parser.readFileAsString(pathToFile));
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
	
	public static String pathForFun(MatlabFunction fun, String pathToDoc){
		return pathToDoc + "/" + fun.getPackageName() + "/" + fun.getName() + ".html";
	}
	
	
}
