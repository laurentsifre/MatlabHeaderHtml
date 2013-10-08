package com.matlabheaderhtml.main;

import java.util.ArrayList;
import java.util.List;

public class MatlabFunction {

	private String name;
	private String packageName = "";
	private String descriptionOneLiner;
	private String usage;
	private String input;
	private String ouput;
	private String description;
	
	private List<String> seeAlsoNames;
	private List<MatlabFunction> seeAlso;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the seeAlso
	 */
	public List<MatlabFunction> getSeeAlso() {
		if (seeAlso == null){
			seeAlso = new ArrayList<MatlabFunction>();
		}
		return seeAlso;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @param seeAlso the seeAlso to set
	 */
	public void setSeeAlso(List<MatlabFunction> seeAlso) {
		this.seeAlso = seeAlso;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatlabFunction [name=" + name + ", descriptionOneLiner="
				+ descriptionOneLiner + ", usage=" + usage + ", input=" + input
				+ ", ouput=" + ouput + ", description=" + description + "]";
	}
	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}
	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}
	/**
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}
	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}
	/**
	 * @return the descriptionOneLiner
	 */
	public String getDescriptionOneLiner() {
		return descriptionOneLiner;
	}
	/**
	 * @param descriptionOneLiner the descriptionOneLiner to set
	 */
	public void setDescriptionOneLiner(String descriptionOneLiner) {
		this.descriptionOneLiner = descriptionOneLiner;
	}
	/**
	 * @return the ouput
	 */
	public String getOuput() {
		return ouput;
	}
	/**
	 * @param ouput the ouput to set
	 */
	public void setOuput(String ouput) {
		this.ouput = ouput;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the seeAlsoNames
	 */
	public List<String> getSeeAlsoNames() {
		return seeAlsoNames;
	}

	/**
	 * @param seeAlsoNames the seeAlsoNames to set
	 */
	public void setSeeAlsoNames(List<String> seeAlsoNames) {
		this.seeAlsoNames = seeAlsoNames;
	}
}
