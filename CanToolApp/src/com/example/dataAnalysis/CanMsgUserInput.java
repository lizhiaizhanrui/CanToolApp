package com.example.dataAnalysis;

import java.util.List;

//用户在GUI上输入的信息
public class CanMsgUserInput {
	public String id;
	public List<String> phyValues;
	public String time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getPhyValues() {
		return phyValues;
	}
	public void setPhyValues(List<String> phyValues) {
		this.phyValues = phyValues;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
