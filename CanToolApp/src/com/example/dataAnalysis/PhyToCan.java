package com.example.dataAnalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhyToCan {
	public CanDB canDB;
	
	public PhyToCan()
	{
		canDB = new CanDB();
	}
	
	public String getCanMsgString(CanMsgUserInput userMsg)
	{
		String result = "";
		//得到数据库解析文件
		Map<String,CanMessage> mapDbc = new HashMap<String,CanMessage>();
		mapDbc = canDB.getCanDbc();
		CanMessage Msg = mapDbc.get(userMsg.id);//获取数据库中该条信息的解析格式；
		
		if(Double.parseDouble(userMsg.id) < 2.147483648E9)
		{
			result += "t";
			String binaryId = Integer.toBinaryString(Integer.parseInt(userMsg.id));
			int binaryId_len = binaryId.length();
			if(binaryId.length() < 11)
			{
				for(int c = 0;c < 11 - binaryId_len;c++)
					binaryId = "0" + binaryId;
			}
			binaryId = binaryId.substring(binaryId.length() - 11,binaryId.length());
			String hex = Integer.toHexString(Integer.parseInt(binaryId, 2));
			int hex_len = hex.length();
			if(hex.length() < 3)
			{
				for(int c = 0;c < 3 - hex_len;c++)
					hex = "0" + hex;
			}
			result += hex.toUpperCase();
			
		}else
		{
			result += "T";
			String binaryId = Integer.toBinaryString(Integer.parseInt(userMsg.id));
			int binaryId_len = binaryId.length();
			if(binaryId.length() < 29)
			{
				for(int c = 0;c < 29 - binaryId_len;c++)
					binaryId = "0" + binaryId;
			}
			binaryId = binaryId.substring(binaryId.length() - 29,binaryId.length());
			String hex = Integer.toHexString(Integer.parseInt(binaryId, 2));
			int hex_len = hex.length();
			if(hex.length() < 8)
			{
				for(int c = 0;c < 8 - hex_len;c++)
					hex = "0" + hex;
			}
			result += hex.toUpperCase();
		}
		result += Msg.DLC;
		int data_size = Integer.parseInt(Character.toString(Msg.DLC),16);
		char data[][] = new char[data_size][8];
		for(int i = 0;i < data_size;i++)
		{
			for(int j = 0;j < 8;j++)
			{
				data[i][j] = '0';
			}
		}
		
		List<String> phyValue = userMsg.getPhyValues();
		List<CanSignal> signalList = Msg.getSignalList();
		for(int k = 0;k < Msg.signalNum;k++)
		{
			String value = phyValue.get(k);
			CanSignal signal = signalList.get(k);
			Double temp = (Double.valueOf(value) - signal.getB()) / signal.getA();
			String binaryX = Integer.toBinaryString((new Double(temp)).intValue());
			int start = signal.getStart();
			int length = signal.getLength();
			int binaryX_len = binaryX.length();
			if(binaryX.length() < length)
			{
				for(int c = 0;c < length - binaryX_len;c++)
					binaryX = "0" + binaryX;
			}
			if(signal.getType().equals("0+"))
			{
				for(int l = 0;l < length;l++)
				{
					data[start / 8][7 - start % 8] = binaryX.charAt(l);
					if(start % 8 != 0)
						start--;
					else
						start += 15;
				}
			}
			else if(signal.getType().equals("1+"))
			{
				for(int l = 0;l < length;l++)
				{
					data[start / 8][7 - start % 8] = binaryX.charAt(length - 1 - l);
					start++;
				}
			}
			else{
				return "failed!";
			}
		}
		int dlc = Integer.valueOf(String.valueOf(Msg.getDLC()));
		for(int d = 0;d < dlc;d++)
		{
			String temp_data = Integer.toHexString(Integer.parseInt(String.valueOf(data[d]), 2));
			int temp_len = temp_data.length();
			if(temp_data.length() < 2)
			{
				for(int c = 0;c < 2 - temp_len;c++)
					temp_data = "0" + temp_data;
			}
			result += temp_data.toUpperCase();
		}
		String time = Integer.toHexString(Integer.parseInt(userMsg.getTime()));
		int time_len = time.length();
		if(time_len < 4)
		{
			for(int c = 0;c < 4 - time_len;c++)
				time = "0" + time;
		}
//		result += time;
		
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

}
