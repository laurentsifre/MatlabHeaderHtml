package com.matlabheaderhtml.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * a static factory method for matlab fun from file
 * @author laurentsifre
 *
 */
public class Parser {


	private static HashMap<String, MatlabFunction> allFunctions = new HashMap<String, MatlabFunction>();


	public static MatlabFunction read(String str){
		str = str.replaceAll("%", "");
		MatlabFunction fun = new MatlabFunction();

		Pattern p = Pattern.compile(" (.*?) ");
		Matcher m = p.matcher(str);
		m.find();
		fun.setName(m.group(1).toLowerCase());

		if (allFunctions.containsKey(fun.getName())){
			System.out.println("WARNING possible duplicate of "+fun.getName());
		} else {
			allFunctions.put(fun.getName(), fun);
		}

		String extracted = readBetweenFlag(str, fun.getName().toUpperCase(), "Usage");
		fun.setDescriptionOneLiner(extracted);

		extracted = readBetweenFlag(str, "Usage", "Input");
		fun.setUsage(extracted);

		extracted = readBetweenFlag(str, "Input", "Output");
		fun.setInput(extracted);
		
		extracted = readBetweenFlag(str, "Output", "Description");
		fun.setOuput(extracted);

		extracted  = readBetweenFlag(str, "Description", "See also");
		fun.setDescription(extracted);
		
		extracted = readBetweenFlag(str, "See also", "function");
		String[] seeAlsoArr = extracted.split(", ");
		
		fun.setSeeAlsoNames(Arrays.asList(seeAlsoArr));
		List<MatlabFunction> retreivedSiblings = new ArrayList<MatlabFunction>(); 
		for (String sibName : seeAlsoArr){
			String sibNameFormat = sibName.toLowerCase().replaceAll(" ","");
			System.out.println("sibname "+ sibNameFormat);
			if (allFunctions.containsKey(sibNameFormat)){
				MatlabFunction retrievedFun = allFunctions.get(sibNameFormat);
				retreivedSiblings.add(retrievedFun);
			}
		}
		fun.setSeeAlso(retreivedSiblings);


		return fun;
	}

	public static String readBetweenFlag(String str, String flag1, String flag2){
		Pattern p = Pattern.compile(flag1+"(.*?)"+flag2);
		Matcher m = p.matcher(str);
		m.find();
		String extracted = "";
		try {
			extracted = m.group(1);
		} catch (IllegalStateException e){
			System.out.println("could not find flag "+flag1);
		}
		return extracted;
	}
	
	public static MatlabFunction readMatlabFunFromFile(String filePath) throws IOException{
		String str;
		str = readFileAsString(filePath);
		MatlabFunction fun = Parser.read(str);
		return fun;
	}

	public static void main(String[] args){
		String pathToFile = "/Users/laurentsifre/Dropbox/these/code/scatnet/scatnet/core/scat.m";
		String str;
		try {
			str = readFileAsString(pathToFile);

			MatlabFunction fun = Parser.read(str);
			System.out.println(fun);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String readFileAsString(String filePath) throws java.io.IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line, results = "";
		while( ( line = reader.readLine() ) != null)
		{
			results += (line+"");
		}
		reader.close();
		return results;
	}
}
