package com.diy.rest.wellnessprogram;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;

//import javax.ws.rs.JsonSerialize;

@XmlRootElement
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {
	private String id;
	private String external_id;
	private String index;
	private String username;
	//@XmlElement(nillable=true)
	private String weight;
	private String sport_activity;
	private String birth_date;
	private String email;
	private String gender;
	private String index_updated_at;
	private IndexHistory index_history;
	private List<WeekNumber> week_numbers;
	public static void main(String[] args) {
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getSport_activity() {
		return sport_activity;
	}
	public void setSport_activity(String sport_activity) {
		this.sport_activity = sport_activity;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIndex_updated_at() {
		return index_updated_at;
	}
	public void setIndex_updated_at(String index_updated_at) {
		this.index_updated_at = index_updated_at;
	}
	public IndexHistory getIndex_history() {
		return index_history;
	}
	public void setIndex_history(IndexHistory index_history) {
		this.index_history = index_history;
	}
	public List<WeekNumber> getWeek_numbers() {
		return week_numbers;
	}
	public void setWeek_numbers(List<WeekNumber> week_numbers) {
		this.week_numbers = week_numbers;
	}
}
