package com.example.dataAnalysis;

import java.io.Serializable;

//用于解析传过来的信号
public class SignalValue implements Serializable{
	public String name;
	public String value;
	public String unit;
	public String nodeName;
	public double C;
	public double D;
	
	public SignalValue()
	{
		
	}
	
	public SignalValue(String name,String value,String unit,String nodeName)
	{
		this.name = name;
		this.value = value;
		this.unit = unit;
		this.nodeName = nodeName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

}
