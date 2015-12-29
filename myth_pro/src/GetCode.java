import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class GetCode {

	public static void parse(String xml){
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点smsReport
            String msgId = rootElt.elementTextTrim("msgId");
            System.out.println(msgId);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] argv){
//		String xml="<xml><msgId>I0001</msgId></xml>";
//		parse(xml);
	}
}
