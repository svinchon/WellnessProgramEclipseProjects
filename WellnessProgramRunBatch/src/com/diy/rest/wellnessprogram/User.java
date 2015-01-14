package com.diy.rest.wellnessprogram;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static void main(String[] args) {
	}
}
