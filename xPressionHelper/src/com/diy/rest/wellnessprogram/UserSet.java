package com.diy.rest.wellnessprogram;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserSet {
	private String name;
	private User[] users;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
	public static void main(String[] args) {
	}
}
