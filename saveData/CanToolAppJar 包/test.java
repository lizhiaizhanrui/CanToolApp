package Main;

// 大数据量导出成XML 
 
import java.io.BufferedOutputStream;  
import java.io.DataOutputStream;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
  
public class test {  
    public static void main(String[] args) {  
        StringBuffer sb = new StringBuffer();  
        try {  
            DataOutputStream rafs = new DataOutputStream(  
                    new BufferedOutputStream(new FileOutputStream(new File(  
                            "d://test.xml"))));  
            sb.append("<?xml version=\"1.0\"?>");  
            sb.append("\n");  
            sb.append("<?mso-application progid=\"Excel.Sheet\"?>");  
            sb.append("\n");  
            sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");  
            sb.append("\n");  
            sb.append("  xmlns:o=\"urn:schemas-microsoft-com:office:office\"");  
            sb.append("\n");  
            sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");  
            sb.append("\n");  
            sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");  
            sb.append("\n");  
            sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");  
            sb.append("\n");  
            sb.append(" <Styles>\n");  
            sb.append("  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");  
            sb.append("   <Alignment ss:Vertical=\"Center\"/>\n");  
            sb.append("   <Borders/>\n");  
            sb.append("   <Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");  
            sb.append("   <Interior/>\n");  
            sb.append("   <NumberFormat/>\n");  
            sb.append("   <Protection/>\n");  
            sb.append("  </Style>\n");  
            sb.append(" </Styles>\n");  
            int sheetcount = 0;  
            int recordcount = 20;  
            int currentRecord = 0;  
            int total = 100;  
            int col = 20;  
            sb.append("<Worksheet ss:Name=\"Sheet0\">");  
            sb.append("\n");  
            sb.append("<Table ss:ExpandedColumnCount=\"" + col  
                    + "\" ss:ExpandedRowCount=\"" + total  
                    + "\" x:FullColumns=\"1\" x:FullRows=\"1\">");  
            sb.append("\n");  
            for (int i = 0; i < total; i++) {  
                if ((currentRecord == recordcount  
                        || currentRecord > recordcount || currentRecord == 0)  
                        && i != 0) {// 一个sheet写满  
                    currentRecord = 0;  
                    rafs.write(sb.toString().getBytes());  
                    sb.setLength(0);  
                    sb.append("</Table>");  
                    sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");  
                    sb.append("\n");  
                    sb.append("<ProtectObjects>False</ProtectObjects>");  
                    sb.append("\n");  
                    sb.append("<ProtectScenarios>False</ProtectScenarios>");  
                    sb.append("\n");  
                    sb.append("</WorksheetOptions>");  
                    sb.append("\n");  
                    sb.append("</Worksheet>");  
                    sb.append("<Worksheet ss:Name=\"Sheet" + i / recordcount  
                            + "\">");  
                    sb.append("\n");  
                    sb.append("<Table ss:ExpandedColumnCount=\"" + col  
                            + "\" ss:ExpandedRowCount=\"" + recordcount  
                            + "\" x:FullColumns=\"1\" x:FullRows=\"1\">");  
                    sb.append("\n");  
                }  
                sb.append("<Row>");  
                for (int j = 0; j < col; j++) {  
                    System.out.println(i);  
                    sb.append("<Cell><Data ss:Type=\"String\">111</Data></Cell>");  
                    sb.append("\n");  
                }  
                sb.append("</Row>");  
                if (i % 5000 == 0) {  
                    rafs.write(sb.toString().getBytes());  
                    rafs.flush();  
                    sb.setLength(0);  
                }  
                sb.append("\n");  
                currentRecord++;  
            }  
            rafs.write(sb.toString().getBytes());  
            sb.setLength(0);  
            sb.append("</Table>");  
            sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");  
            sb.append("\n");  
            sb.append("<ProtectObjects>False</ProtectObjects>");  
            sb.append("\n");  
            sb.append("<ProtectScenarios>False</ProtectScenarios>");  
            sb.append("\n");  
            sb.append("</WorksheetOptions>");  
            sb.append("\n");  
            sb.append("</Worksheet>");  
            sb.append("</Workbook>");  
            sb.append("\n");  
            rafs.write(sb.toString().getBytes());  
            rafs.flush();  
            rafs.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  