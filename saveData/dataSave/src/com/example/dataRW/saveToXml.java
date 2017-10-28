package com.example.dataRW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 





import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


 
/*把CanMsgValue对象保存到xml文件*/
public class saveToXml {
	//public CanMsgValue canmsgvalue;
	
	public static void save(CanMsgValue canmsgvalu){
		CanMsgValue canmsgvalue=canmsgvalu;
		//定义 canmsgvalue赋值
		 String id;
		 String name;
		 char DLC;
		 String Dir;//即为发送的Node名称
		 String Data;
		 int sigValueNum;
		 List<SignalValue> SigValueList;
		 id=canmsgvalue.getId();
		 name=canmsgvalue.getName();
		 DLC=canmsgvalue.getDLC();
		 Dir=canmsgvalue.getDir();
		 Data=canmsgvalue.getData();
		 sigValueNum=canmsgvalue.getSigValueNum();
		 SigValueList=canmsgvalue.getSigValueList();
		 
		 DocumentBuilderFactory  fct=DocumentBuilderFactory.newInstance();
		 try {
	            DocumentBuilder bui=fct.newDocumentBuilder();
	            Document doc=bui.newDocument();
	            
//              创建标签和文本节点	            
	            Element cv=doc.createElement("canmsgvalues");

	            Element c1=doc.createElement("canmsgvalue");
	            Attr id1=doc.createAttribute("id");
	            id1.setNodeValue(id);
//	            Attr id2=doc.createAttribute("id");
//	            id2.setNodeValue("2");
	            Element name1=doc.createElement("name");
	            Text na1=doc.createTextNode(name);
	            Element dlc1=doc.createElement("DLC");
	            Text dl1=doc.createTextNode( String.valueOf(DLC));
//	            Element sex2=doc.createElement("sex");
//	            Text se2=doc.createTextNode("妹子");
	            Element dir1=doc.createElement("Dir");
	            Text di1=doc.createTextNode(Dir);
	            Element date1=doc.createElement("Date");
	            Text da1=doc.createTextNode(Data);
	            Element sig1=doc.createElement("sigValueNum");
	            Text si1=doc.createTextNode(String.valueOf(sigValueNum));
	            Element sv=doc.createElement("SignalValues");
//	                              建立节点关系
	            doc.appendChild(cv);
	                cv.appendChild(c1);
	                    c1.appendChild(name1);
	                        c1.setAttributeNode(id1);
	                        name1.appendChild(na1);
	                    c1.appendChild(dlc1);
	                        dlc1.appendChild(dl1);
	                    c1.appendChild(dir1);
	                        dir1.appendChild(di1);
	                    c1.appendChild(date1);
	                        date1.appendChild(da1);
	                    c1.appendChild(sig1);
	                        sig1.appendChild(si1);
	                    c1.appendChild(sv);

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
//	                        
	                        
//	            ps.appendChild(p2);
//	                    p2.appendChild(name2);
//	                        p2.setAttributeNode(id2);
//	                        name2.appendChild(na2);
//	                    p2.appendChild(sex2);
//	                        sex2.appendChild(se2);
	                        
	            try {
//	            	创建输出流
	                FileOutputStream fos=new FileOutputStream(new File("E://message2.xml"));
	            	
	            	//FileOutputStream fos=new FileOutputStream(new File("data/data/com.example.dataSave/longdada.xml"));
	                 
	                try {
//	                	写出到文件
	                    ((org.apache.crimson.tree.XmlDocument)doc)
	                    .write(fos);
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                try {
	                    fos.flush();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                try {
	                    fos.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                 
	                 
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	             
	             
	             
	             
	             
	        } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		 
		 
		 System.out.println("新建文件并保存数据");
		
	
   
         
    }
    
}