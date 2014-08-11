package com.au.assign;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "institutes")
public class Institutions {

	@XmlElement(name = "institution", type = Institution.class)
	private List<Institution> institutes = new ArrayList<Institution>();

	public Institutions() {

	}

	public Institutions(List<Institution> institutes) {
		this.institutes = institutes;
	}

	public List<Institution> getInstitutions() {
		return institutes;
	}

	public void setInstitutions(List<Institution> institutes) {
		this.institutes = institutes;
	}

}
