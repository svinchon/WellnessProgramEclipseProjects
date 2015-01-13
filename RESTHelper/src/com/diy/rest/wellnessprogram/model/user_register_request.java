package com.diy.rest.wellnessprogram.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class user_register_request {
	private User user_register;
	public User getUser_register() { return user_register; }
	public void setUser_register(User user_register) { this.user_register = user_register; }
}
