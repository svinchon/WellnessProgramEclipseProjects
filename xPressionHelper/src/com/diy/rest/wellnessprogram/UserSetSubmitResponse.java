package com.diy.rest.wellnessprogram;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserSetSubmitResponse {
	private String status;
	public static void main(String[] args) {
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
