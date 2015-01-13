package com.diy.rest.wellnessprogram.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML (and JSON in theory)
public class User {
	private String id;
	private String username;
	private String external_id;
	private double index;
	private String email;
	private String password;
	private String gender;
	private double weight;
	private String birth_date;
	private int sports_activity;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getExternal_id() { return external_id; }
	public void setExternal_id(String external_id) { this.external_id = external_id; }
	public double getIndex() { return index; }
	public void setIndex(double index) { this.index = index; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; 	}
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getGender() { return gender;	}
	public void setGender(String gender) { this.gender = gender; }
	public double getWeight() { return weight;	}
	public void setWeight(double weight) { this.weight = weight; }
	public String getBirth_date() { return birth_date;	}
	public void setBirth_date(String birth_date) { this.birth_date = birth_date; }
	public int getSports_activity() { return sports_activity; }
	public void setSports_activity(int sportsActivity) { this.sports_activity = sportsActivity; }
} 
