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


	public enum State{
		NOT_SET,
		USAGE,
		INPUT,
		OUTPUT,
		DESCRIPTION,
		SEEL_ALSO,
	}
	
	public static MatlabFunction readLinePerLine(String header){
		// get the name
		header = header.replaceAll("%", "");
		String[] lines = header.split("\n");
		if (lines.length == 0){
			return null;
		}
		String[] firstLineSplit = lines[0].split(" ");
		if (firstLineSplit.length < 1){
			System.out.println("warning : first line should contain funtion name");
			return null;
		}
		String name = firstLineSplit[1].toLowerCase();
		// get the one liner descr as the rest of the first line
		StringBuffer oneLinerDescrBuff = new StringBuffer();
		for (int i=2; i<firstLineSplit.length; i++){
			oneLinerDescrBuff.append(firstLineSplit[i]);
			oneLinerDescrBuff.append(" ");
		}
		String oneLinerDescr = oneLinerDescrBuff.toString();
		// retrieves the function given its name if already parse
		MatlabFunction fun;
		if (allFunctions.containsKey(name)){
			//fun = allFunctions.get(name);
			fun = new MatlabFunction();
			fun.setName(name);
		} else {
			fun = new MatlabFunction();
			fun.setName(name);
			fun.setDescriptionOneLiner(oneLinerDescr);
			allFunctions.put(name, fun);
		}

		State state = State.NOT_SET;
		// loop over the rest of the lines 
		for (int i=1; i<lines.length; i++){
			String currLine = lines[i];
			String[] split = currLine.split(" ");
			boolean hasChangedState = false;
			if (split.length > 1){
				String firstWord = split[1];
				if (firstWord.equalsIgnoreCase("usage")){
					state = State.USAGE;
					hasChangedState = true;
				}
				if (firstWord.equalsIgnoreCase("input")){
					state = State.INPUT;
					hasChangedState = true;
				}
				if (firstWord.equalsIgnoreCase("output")){
					state = State.OUTPUT;
					hasChangedState = true;
				}
				if (firstWord.equalsIgnoreCase("description")){
					state = State.DESCRIPTION;
					hasChangedState = true;
				}
				if (firstWord.equalsIgnoreCase("see")){
					state = State.SEEL_ALSO;
					hasChangedState = true;
				}
				if (!hasChangedState){
					String escapeStr = "\n";
					switch (state) {
					case USAGE:
						fun.setUsage(fun.getUsage()+currLine+escapeStr);
						break;
					case INPUT:
						fun.setInput(fun.getInput()+currLine+escapeStr);
						break;
					case OUTPUT:
						fun.setOuput(fun.getOuput()+currLine+escapeStr);
						break;
					case DESCRIPTION:
						fun.setDescription(fun.getDescription()+currLine+escapeStr);
						break;
					case SEEL_ALSO:
						String[] seeAlsoArr = currLine.split(", ");
						fun.setSeeAlsoNames(Arrays.asList(seeAlsoArr));
						List<MatlabFunction> retreivedSiblings = new ArrayList<MatlabFunction>(); 
						for (String sibName : seeAlsoArr){
							String sibNameFormat = sibName.toLowerCase().replaceAll(" ","");
							if (allFunctions.containsKey(sibNameFormat)){
								MatlabFunction retrievedFun = allFunctions.get(sibNameFormat);
								retreivedSiblings.add(retrievedFun);
							}
						}
						fun.setSeeAlso(retreivedSiblings);
						break;
					default:
						break;
					}
				}
			}
		}
		return fun;
		
	}


	
	public static MatlabFunction readMatlabFunFromFile(String filePath) throws IOException{
		String str;
		str = readFileAsString(filePath);
		//MatlabFunction fun = Parser.read(str);
		MatlabFunction fun = Parser.readLinePerLine(str);
		return fun;
	}


	public static String readFileAsString(String filePath) throws java.io.IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line, results = "";
		while( ( line = reader.readLine() ) != null){
			results += (line+"\n");
		}
		reader.close();
		return results;
	}
}
