package com.matlabheaderhtml.main;

import java.util.ArrayList;
import java.util.List;

public class MatlabFunction {

	private String name = "";
	private String packageName = "";
	private String descriptionOneLiner = "";
	private String usage = "";
	private String input = "";
	private String ouput = "";
	private String description = "";
	
	private MatlabPackage matlabPackage;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatlabFunction other = (MatlabFunction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the matlabPackage
	 */
	public MatlabPackage getMatlabPackage() {
		return matlabPackage;
	}

	/**
	 * @param matlabPackage the matlabPackage to set
	 */
	public void setMatlabPackage(MatlabPackage matlabPackage) {
		this.matlabPackage = matlabPackage;
	}
}
