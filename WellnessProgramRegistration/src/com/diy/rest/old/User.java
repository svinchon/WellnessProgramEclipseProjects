package com.diy.rest.old;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML (and JSON in theory)
public class User {
	private String id;
	private String username;
	private String externalId;
	private double index;
	private String email;
	private String password;
	private String gender;
	private double weight;
	private String birth_date;
	private int sportsActivity;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getExternalId() { return externalId; }
	public void setExternalId(String externalId) { this.externalId = externalId; }
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
	public String getBirthDate() { return birth_date;	}
	public void setBirthDate(String birthDate) { this.birth_date = birthDate; }
	public int getSportsActivity() { return sportsActivity; }
	public void setSportsActivity(int sportsActivity) { this.sportsActivity = sportsActivity; }
} 
