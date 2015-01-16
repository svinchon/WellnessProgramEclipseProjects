package com.diy.rest.wellnessprogram;

import java.util.List;

public class IndexHistory {
	private String last_value_date;
	private List<Value> values;
	public String getLast_value_date() {
		return last_value_date;
	}
	public void setLast_value_date(String last_value_date) {
		this.last_value_date = last_value_date;
	}
	public List<Value> getValues() {
		return values;
	}
	public void setValues(List<Value> values) {
		this.values = values;
	}
}
