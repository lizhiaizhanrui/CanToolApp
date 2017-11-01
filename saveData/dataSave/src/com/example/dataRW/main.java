package com.example.dataRW;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SignalValue sv1 = new SignalValue("signal1", "5", "10011010011", "ECU1");
		SignalValue sv2 = new SignalValue("signal2", "6", "10011010011", "ECU2");
		SignalValue sv3 = new SignalValue("signal3", "7", "10011010011", "ECU3");
		SignalValue sv4 = new SignalValue("signal4", "8", "10011010011", "ECU4");
		SignalValue sv5= new SignalValue("signal5", "9", "10011010011", "ECU5");
		List<SignalValue> sigList1 = new ArrayList<SignalValue>();
		List<SignalValue> sigList2 = new ArrayList<SignalValue>();
		sigList1.add(sv1);
		sigList1.add(sv2);
		sigList1.add(sv3);
		sigList2.add(sv4);
		sigList2.add(sv5);
		CanMsgValue cmv1 = new CanMsgValue("0xFE", "canName1", 'A', "Hello", "110100110110", 5, sigList1);
		CanMsgValue cmv2 = new CanMsgValue("0xFE78", "canName2", 'B', "Hello", "110100110110", 5, sigList2);
		
	   //saveToXml.save(cmv2);
       //insertTest.insertXml(cmv1);
       List<CanMsgValue> cmv3=readXml.xmlToCanMsgValue();
	   saveToXml.save(cmv3.get(0));
	   insertTest.insertXml(cmv3.get(1));
	   

	}

}
