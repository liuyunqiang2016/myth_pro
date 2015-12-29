package biz.common.tools;

import java.io.BufferedReader;
import java.io.FileReader;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class XmlTools
{
	public static Document loadXMLDocument(String path) throws Exception
	{
		String strXml = "";
		FileReader objFile = new FileReader(path);
		BufferedReader objBuffer = new BufferedReader(objFile);
		String strLine = objBuffer.readLine();
		while (strLine != null)
		{
			strXml += strLine;
			strLine = objBuffer.readLine();
		}
		objBuffer.close();
		Document doc = DocumentHelper.parseText(strXml);
		return doc;
	}
}
