package com.example.dataRW;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class insertTest {

	 /** 
     * 向已存在的xml文件中插入元素 
     */  
    public static void insertXml(CanMsgValue canmsg){  
        //String flage="false";  
   
      //定义 canmsgvalue赋值
         CanMsgValue canmsgvalue=canmsg;
		 String id=canmsgvalue.getId();
		 String name=canmsgvalue.getName();
		 char DLC=canmsgvalue.getDLC();
		 String Dir=canmsgvalue.getDir();//即为发送的Node名称
		 String Data=canmsgvalue.getData();
		 int sigValueNum=canmsgvalue.getSigValueNum();
		 List<SignalValue> SigValueList=canmsgvalue.getSigValueList();
		 
		 
		
          
        try {  
            //得到DOM解析器的工厂实例  
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  
            //从DOM工厂中获得DOM解析器  
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();  
            //把要解析的xml文档读入DOM解析器  
            Document doc = dbBuilder.parse("E://message2.xml");  
            
            //得到文档名称为person的元素的节点列表  
            NodeList nList = doc.getElementsByTagName("canmsgvalues");  
            Element canmsgvalues = (Element)nList.item(0);  
            //创建名称为Student的元素  
            Element canmsgvalue1 = doc.createElement("canmsgvalue");  
            //设置元素Student的属性值为231  
            //canmsgvalue.setAttribute("id", id);  
            Attr id1=doc.createAttribute("id");
            id1.setNodeValue(id);
         /*   //创建名称为name的元素  
            Element name = doc.createElement("name");  
            //创建名称为 香香 的文本节点并作为子节点添加到name元素中  
            name.appendChild(doc.createTextNode("香香"));  
              */
//          创建标签和文本节点	  
            Element name1=doc.createElement("name");
            Text na1=doc.createTextNode(name);
            Element dlc1=doc.createElement("DLC");
            Text dl1=doc.createTextNode( String.valueOf(DLC));
//            Element sex2=doc.createElement("sex");
//            Text se2=doc.createTextNode("妹子");
            Element dir1=doc.createElement("Dir");
            Text di1=doc.createTextNode(Dir);
            Element date1=doc.createElement("Date");
            Text da1=doc.createTextNode(Data);
            Element sig1=doc.createElement("sigValueNum");
            Text si1=doc.createTextNode(String.valueOf(sigValueNum));
            Element sv=doc.createElement("SignalValues");

//            建立节点关系
//            doc.appendChild(canmsgvalues);
            canmsgvalues.appendChild(canmsgvalue1);
            canmsgvalue1.appendChild(name1);
            canmsgvalue1.setAttributeNode(id1);
                   name1.appendChild(na1);
                   canmsgvalue1.appendChild(dlc1);
                   dlc1.appendChild(dl1);
                   canmsgvalue1.appendChild(dir1);
                   dir1.appendChild(di1);
                   canmsgvalue1.appendChild(date1);
                   date1.appendChild(da1);
                   canmsgvalue1.appendChild(sig1);
                   sig1.appendChild(si1);
                   canmsgvalue1.appendChild(sv);

      for(int i=0;i<SigValueList.size();i++){
               
               SignalValue sig=SigValueList.get(i);
     
               String sid=String.valueOf(i+1);
               String signame;
               String value;
               String unit;
               String nodeName;
               double C;
               double D;
               signame=sig.getName();
               value=sig.getValue();
               unit=sig.getUnit();
               nodeName=sig.getNodeName();
               C=sig.getC();
               D=sig.getD();
               Element s1=doc.createElement("signalvalue");
               Attr sid1=doc.createAttribute("id");
               sid1.setNodeValue(String.valueOf(i+1));
               Element signame1=doc.createElement("signame");
	            Text sna1=doc.createTextNode(signame);
	            Element value1=doc.createElement("value");
	            Text va1=doc.createTextNode(value);
	            Element unit1=doc.createElement("unit");
	            Text un1=doc.createTextNode(unit);
	            Element nodename1=doc.createElement("nodename");
	            Text no1=doc.createTextNode(nodeName);
	            Element sigc1=doc.createElement("c");
	            Text sc1=doc.createTextNode(String.valueOf(C));
	            Element sigd1=doc.createElement("d");
	            Text sd1=doc.createTextNode(String.valueOf(D));
            
        sv.appendChild(s1);
      	s1.appendChild(signame1);
          	s1.setAttributeNode(sid1);
          	signame1.appendChild(sna1);
           s1.appendChild(value1);
              value1.appendChild(va1);
          s1.appendChild(unit1);
              unit1.appendChild(un1);
          s1.appendChild(nodename1);
              nodename1.appendChild(no1);   
          s1.appendChild(sigc1);
              sigc1.appendChild(sc1);
          s1.appendChild(sigd1);
              sigd1.appendChild(sd1);
      }      
            
     
            //将内存中的文档通过文件流生成insertSchool.xml,XmlDocument位于crison.jar下  
            ((XmlDocument)doc).write(new FileOutputStream("E://message2.xml"));  
            System.out.println("数据插入成功");  
              
          //  flage="true";  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        //return flage;  
    }  
}
