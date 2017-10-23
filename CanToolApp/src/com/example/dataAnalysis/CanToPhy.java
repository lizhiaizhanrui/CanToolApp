package com.example.dataAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class CanToPhy {
	
	public CanDB canDB;
	
	public CanToPhy()
	{
		canDB = new CanDB();
	}
	
//	//获取can信息数据库的信息，并转换成Map
//	public static Map<String,CanMessage> getMsgDbc(File file)
//	{
//		  StringBuilder result = new StringBuilder();
//	      try{
//	            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//	            String s = null;
//	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//	                result.append(System.lineSeparator()+s);
//	            }
//	            br.close();    
//	      }catch(Exception e){
//	            e.printStackTrace();
//	      }
//	      String data[] = result.toString().split("BO_ ");
//		  Map<String,CanMessage> dataMap = new HashMap<String,CanMessage>();
//		  for(int i = 1;i < data.length;i++)
//		  {
//			  String temp[] = data[i].split(" ");
//			  CanMessage message = new CanMessage(temp[0],temp[1].substring(0, temp[1].length() - 1),temp[2].charAt(0),temp[3]);
//			  String signals[] = data[i].split("SG_ ");
//			  message.setSignalNum(signals.length - 1);
//			  List<CanSignal> listSig = new ArrayList<CanSignal>();
//			  for(int k = 1;k < signals.length;k++)
//			  {
//				  String sigTemp[] = signals[k].split(" ");
//				  CanSignal sig = new CanSignal();
//				  sig.setName(sigTemp[0]);
//				  sig.setStart(Integer.parseInt(sigTemp[2].split("\\|")[0]));
//				  sig.setLength(Integer.parseInt(sigTemp[2].split("\\|")[1].split("@")[0]));
//				  sig.setType(sigTemp[2].split("\\|")[1].split("@")[1]);
//				  sig.setA(Double.valueOf(sigTemp[3].substring(1, sigTemp[3].length() - 1).split(",")[0]).doubleValue());
//				  sig.setB(Double.valueOf(sigTemp[3].substring(1, sigTemp[3].length() - 1).split(",")[1]).doubleValue());
//				  sig.setC(Double.valueOf(sigTemp[4].substring(1, sigTemp[4].length() - 1).split("\\|")[0]).doubleValue());
//				  sig.setD(Double.valueOf(sigTemp[4].substring(1, sigTemp[4].length() - 1).split("\\|")[1]).doubleValue());
//				  sig.setUnit(sigTemp[5].substring(1, sigTemp[5].length() - 1));
//				  sig.setNodeName(sigTemp[sigTemp.length - 1]);
//				  listSig.add(sig);
//			  }
//			  message.setSignalList(listSig);
//			  dataMap.put(temp[0],message);
//		  }
//	      canDB = dataMap;
//		  return dataMap;
//	 }
	
	//将can信息中的data转换成二进制表
	public char[][] getBinaryMap(String data,int length)
	{
		char result[][] = new char[length][8];
		for(int i = 0;i < length;i++)
		{
			for(int l = 0;l < 8;l++)
			{
				result[i][l] = '0';
			}
		}
		
		for(int i = 0;i < length;i++)
		{
//		    String binary = hexStr2BinArr(data.substring(data.length()- 2 * (i + 1), data.length() - 2 * i));
			String temp = data.substring(2 * i,2 * (i + 1));
			String binary = hexStr2BinArr(temp);
		    for(int k = 0;k < 8;k++)
		    {
		    	result[i][k] = binary.charAt(k);
		    }
		}
		return result;
	}
	
	//将16进制数转换成二进制
	public String hexStr2BinArr(String hexString){  
		 if (hexString == null || hexString.length() % 2 != 0)  
			 return null;  
			 String bString = "", tmp;  
			 for (int i = 0; i < hexString.length(); i++)  
			 {  
				 tmp = "0000"  + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));  
			 bString += tmp.substring(tmp.length() - 4);  
			 }  
			 return bString;  

	    }
	 
	 //将获取的can信息解析成物理信息值存储在类中，以便用于显示。
	 public CanMsgValue getMessageValue(String message)
	 {
		 CanMsgValue msgValue = new CanMsgValue();
		 
		 Map<String,CanMessage> mapDbc = new HashMap<String,CanMessage>();
		 mapDbc = canDB.getCanDbc();
		 String id = "";
		 String dec_id = "";
		 if(message.charAt(0) == 't')
		 {
			 id = message.substring(1,4);
			 dec_id = String.valueOf(Integer.parseInt(id,16));
		 }
		 else if(message.charAt(0) == 'T')
		 {
			 id = message.substring(1,9);
			 dec_id = hexStr2BinArr(id);
			 dec_id = "1" + dec_id.substring(1);
			 dec_id = String.valueOf(Integer.parseInt(dec_id,2));
		 }
	
		 CanMessage msgModel = mapDbc.get(dec_id);
		 int size = mapDbc.size();
		 msgValue.setId(id);
		 msgValue.setName(msgModel.name);
		 msgValue.setDLC(message.charAt(4));
		 msgValue.setDir(msgModel.nodeName);
		 msgValue.setData(message.substring(5,message.length()));
		 msgValue.setSigValueNum(msgModel.signalNum);
		 
		 char[][] binaryMap = getBinaryMap(msgValue.Data,Integer.parseInt(Character.toString(msgValue.DLC),16));
//		 for(int i = 0;i < 8;i++)
//		 {
//			 String temp = "";
//			 for(int k = 0;k < 8;k++)
//			 {
//				 temp += Character.toString(binaryMap[i][k]);
//			 }
//			 System.out.println(temp);
//		 }
		 
		 List<SignalValue> SigValueList = new ArrayList<SignalValue>();
		 for(int i = 0;i < msgModel.signalNum;i++)
		 {
			 CanSignal sigModel = msgModel.getSignalList().get(i);
			 int start = sigModel.start;
			 int length = sigModel.length;
			 String type = sigModel.type;
			 String value = "";
			 start = 8 * (start / 8) + 8 * (start / 8 + 1) - 1 - start;
			 if(type.equals("0+"))
			 {		 
				 for(int k = 0;k < length;k++)
				 {
					 value = value + binaryMap[start / 8][start % 8];
					 start++;
				 }
				 
			 }else if(type.equals("1+"))
			 {
				 for(int k = 0;k < length;k++)
				 {
					 value = binaryMap[start / 8][start % 8] + value;
					 if(start % 8 == 0)
						 start += 15;
					 else
						 start--;
				 }
			 }
			 value = Double.toString((Integer.parseInt(value,2) * sigModel.A + sigModel.B));
			 
			 SignalValue sigValue = new SignalValue();
			 sigValue.name = sigModel.name;
			 sigValue.value = value;
			 sigValue.unit = sigModel.unit;
			 sigValue.nodeName = sigModel.nodeName;
			 sigValue.C = sigModel.C;
			 sigValue.D = sigModel.D;
			 SigValueList.add(sigValue);
		 }
		 msgValue.setSigValueList(SigValueList);
		 return msgValue;
	 }

}
