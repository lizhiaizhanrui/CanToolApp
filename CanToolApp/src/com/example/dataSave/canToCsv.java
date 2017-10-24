package com.example.dataSave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/*将can信息保存为csv文件*/
public class canToCsv {
	public static void saveToCsv(String str){
		 try {  
			    String canStr=str;
			    //File csv = new File("E://canMessage.csv"); // CSV文件  
			    File csv = new File("data/data/com.example.dataSave/canMessage.csv");
	            // 追记模式  
	            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));  
	            // 新增一行数据  
	            bw.newLine();  
	            //bw.write("三枪拍案惊奇" + "," + "2009" + "," + "1212");  
	            bw.write(canStr); 
	            bw.write(","); 
	            bw.close();  
	        } catch (FileNotFoundException e) {  
	            // 捕获File对象生成时的异常  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // 捕获BufferedWriter对象关闭时的异常  
	            e.printStackTrace();  
	        }  
	}
	/*public static void main(String[] args) { 
		String strtest = "三枪拍案惊奇";
		saveToCsv(strtest);
	}*/

}
