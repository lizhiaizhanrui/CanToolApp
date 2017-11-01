package com.example.dataRW;

/*DOM4J 方式解析XML*/

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class readXml {
	
   public static List<CanMsgValue> xmlToCanMsgValue(){
	    List<CanMsgValue> canList = new ArrayList();
		CanMsgValue canmsgvalue1=new CanMsgValue();
		SignalValue signalValue1=new SignalValue();
		List<SignalValue> SigValueList1=new ArrayList<SignalValue>();
		
		 // 解析xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
        	//CanMsgValue canmsgvalue1;
            // 通过reader对象的read方法加载xml文件,获取docuemnt对象。
            Document document = reader.read(new File("E://message1.xml"));
        	//Document document = reader.read(new File("data/data/com.example.dataSave/longdada.xml"));
        	
            // 通过document对象获取根节点canmsgvalues
            Element canmsgvalues = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = canmsgvalues.elementIterator();
            // 遍历迭代器，获取根的子节点中的信息（canmsgvalue）
            while (it.hasNext()) {
                 // System.out.println("=====开始遍历某个canmsgvalue=====");
                  Element canmsgvalue = (Element) it.next();
                 // 获取canmsgvalue的属性名以及 属性值,赋值给canmsgvalue1的id字段
                 List<Attribute> canmsgAttrs = canmsgvalue.attributes();
                 for (Attribute attr : canmsgAttrs) {
                       //System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
            	        canmsgvalue1.setId(attr.getValue());
                  }
            
            
                 // 根据节点名获取canmsgvalue的子节点值 ,并赋值给canmsgvalue1
                  canmsgvalue1.setName(canmsgvalue.elementText("name"));
                  canmsgvalue1.setDLC(canmsgvalue.elementText("DLC").charAt(0));
                  canmsgvalue1.setDir(canmsgvalue.elementText("Dir"));
                  canmsgvalue1.setData(canmsgvalue.elementText("Date"));
                  canmsgvalue1.setSigValueNum(Integer.parseInt(canmsgvalue.elementText("sigValueNum")));
             
                  //获取SignalValues标签
                  Element signals =canmsgvalue.element("SignalValues");
                  // Element signalx =signals.element("SignalValue");
                  //获取所有SignalValue标签，放入signalList中
                  List<Element> signalList =signals.elements();
                  //循环把signalValue的子标签名对应的值，赋值给signalValue1对象的字段
                  Element signalx;
                  for(int i=0;i<signalList.size();i++){
            	      signalx =signalList.get(i);
            	     //SignalValue signalValue1;
            	      signalValue1.setName(signalx.elementText("signame"));
            	      signalValue1.setUnit(signalx.elementText("unit"));
            	      signalValue1.setValue(signalx.elementText("value"));
            	      signalValue1.setNodeName(signalx.elementText("nodename"));
            	      signalValue1.setC(Double.parseDouble(signalx.elementText("c")));
            	      signalValue1.setD(Double.parseDouble(signalx.elementText("d")));
            	      //signalValue1放入SigValueList1中
            	      SigValueList1.add(signalValue1);
                   }
                  //SigValueList1赋值给canmsgvalue1的相应字段
                   canmsgvalue1.setSigValueList(SigValueList1); 
                   canList.add(canmsgvalue1);
           }
         
         
       } catch (DocumentException e) {
           e.printStackTrace();
       }
        System.out.println("xml解析成功");
        return canList;	 
        
  }
}
