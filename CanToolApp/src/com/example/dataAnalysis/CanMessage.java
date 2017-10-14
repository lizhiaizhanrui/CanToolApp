package com.example.dataAnalysis;

import java.util.List;

//数据库中信息的解析格式
public class CanMessage {
	public String id;
	public String name;
	public char DLC;
	public String nodeName;
	public int signalNum;
	public List<CanSignal> signalList;
	
	CanMessage(String id,String name,char DLC,String nodeName)
	{
		this.id = id;
		this.name = name;
		this.DLC = DLC;
		this.nodeName = nodeName;
		this.signalNum = signalNum;
		this.signalList = signalList;
	}
	
	CanMessage(String id,String name,char DLC,String nodeName,int signalNum,List<CanSignal> signalList)
	{
		this.id = id;
		this.name = name;
		this.DLC = DLC;
		this.nodeName = nodeName;
		this.signalNum = signalNum;
		this.signalList = signalList;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public char getDLC() {
		return DLC;
	}
	public void setDLC(char dLC) {
		DLC = dLC;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public int getSignalNum() {
		return signalNum;
	}
	public void setSignalNum(int signalNum) {
		this.signalNum = signalNum;
	}
	public List<CanSignal> getSignalList() {
		return signalList;
	}
	public void setSignalList(List<CanSignal> signalList) {
		this.signalList = signalList;
	}
}
