package biz.common.net.tcp;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import biz.common.BGMLogger;
import biz.common.data.webservice.DataElement;
import biz.common.data.webservice.DataList;
import biz.common.format.CommFormat;
import biz.common.tools.DataTransform;
import biz.common.tools.XmlTools;

public class TCPIPServiceFactory
{
	/**
	 * XML DOM Document 对象
	 */
	protected Document document;

	private Map services;

	private String confFilePath;

	public String getConfFilePath()
	{
		return confFilePath;
	}

	public void setConfFilePath(String confFilePath)
	{
		this.confFilePath = confFilePath;
	}

	public Map getServices()
	{
		return services;
	}

	public void setServices(Map services)
	{
		this.services = services;
	}

	public TCPIPServiceFactory()
	{
		this.setServices(new HashMap());
	}

	public void initializeServiceFactory(String fileName)
	{
		try
		{
			this.document = XmlTools.loadXMLDocument(fileName);
			initTCPIPService();
		} catch (Exception e)
		{
			BGMLogger.LogError("initialize serviceList is fail:"
					+ e.getMessage());
		}
	}

	public void initTCPIPService()
	{
		Element stylesElement = document.getRootElement();

		// TCPIPServerService节点解析
		List services = stylesElement.elements("TCPIPServerService");
		for (Iterator ite = services.iterator(); ite.hasNext();)
		{
			Element serviceElement = (Element) ite.next();
			TCPIPServiceServer tcpServer = new TCPIPServiceServer();
			Element listenEle = serviceElement.element("ListenPort");
			String strServiceName = serviceElement.attributeValue("name");
			tcpServer.setServiceName(strServiceName);
			int iPort = DataTransform.TransToInt(listenEle.attributeValue(
					"port").toString());
			tcpServer.setPort(iPort);
			int iMaxConnection = DataTransform.TransToInt(listenEle
					.attributeValue("maxConnection").toString());
			tcpServer.setMaxConnection(iMaxConnection);
			boolean bPoolThread = DataTransform.TransToBool(listenEle
					.attributeValue("poolThread").toString());
			tcpServer.setPoolThread(bPoolThread);
			int iPoolSize = DataTransform.TransToInt(listenEle.attributeValue(
					"poolSize").toString());
			tcpServer.setPoolSize(iPoolSize);
			boolean bKeepAlive = DataTransform.TransToBool(listenEle
					.attributeValue("keepAlive").toString());
			tcpServer.setKeepAlive(bKeepAlive);

			Element comProEle = serviceElement.element("CommProcessor");
			String strCommProcessor = comProEle.getText().trim();
			Class objClass;
			try
			{
				objClass = Class.forName(strCommProcessor);
				Object objDao = objClass.getConstructor().newInstance();
				tcpServer.setCommProcessor((CommProcessor) objDao);
			} catch (Exception e)
			{
				BGMLogger.LogInfo("load CommProcessor class is fail:"
						+ e.getMessage());
			}

			Element formatEle = serviceElement.element("FormatStyle");
			String strCommFormatClass = DataTransform.Trim(formatEle
					.attributeValue("class"));
			String strSplitChar = DataTransform.Trim(formatEle
					.attributeValue("splitChar"));
			try
			{
				objClass = Class.forName(strCommFormatClass);
				Object objDao = objClass.getConstructor().newInstance();
				if (strSplitChar != null)
				{
					Method setMethod = objClass.getMethod("setSplitChar",
							new Class[] { String.class });
					setMethod.invoke(objDao, new Object[] { strSplitChar });
				}
				tcpServer.setCommFormat((CommFormat) objDao);
			} catch (Exception e)
			{
				BGMLogger.LogInfo("load CommFormat class is fail:"
						+ e.getMessage());
			}

			try
			{
				Map servceMap = initServiceList(stylesElement, strServiceName);
				tcpServer.setServerMap(servceMap);
			} catch (Exception ex)
			{
				BGMLogger.LogError("Init TCPIPServiceServer name["
						+ strServiceName + "] port[" + iPort + "] is fail:"
						+ ex.getMessage());
			}
			BGMLogger.LogInfo("Init TCPIPServiceServer name[" + strServiceName
					+ "] port[" + iPort + "]");
			this.services.put(strServiceName, tcpServer);
		}
	}

	public Map initServiceList(Element stylesElement, String strServiceName)
			throws Exception
	{
		// TCPIPServiceDef节点解析
		Map servceMap = new HashMap();
		List serviceDefList = stylesElement.elements("TCPIPServiceDef");
		for (Iterator ite = serviceDefList.iterator(); ite.hasNext();)
		{
			Element serviceElement = (Element) ite.next();
			if (serviceElement.attributeValue("ServiceID").toString().trim()
					.equals(strServiceName))
			{
				List serviceMap = serviceElement.elements("MapEntry");
				servceMap.put(strServiceName, initMapEntry(serviceMap));
			}
		}
		return servceMap;
	}

	// public Map initMapEntry(List serviceMap, List serviceList)
	public Map initMapEntry(List serviceMap) throws Exception
	{
		Map entryMap = new HashMap();
		for (Iterator ite = serviceMap.iterator(); ite.hasNext();)
		{
			Element serviceElement = (Element) ite.next();
			TCPIPServerModel model = new TCPIPServerModel();
			model.setBeanName(serviceElement.attributeValue("BeanName"));
			model.setKey(serviceElement.attributeValue("key"));
			model.setValue(serviceElement.attributeValue("value"));
			model.setOpName(serviceElement.attributeValue("OpName"));
			Element mapElement = findServerByID(serviceElement
					.attributeValue("value"));
			BGMLogger.LogInfo("TCPIP Seivece key[" + model.getKey()
					+ "] value[" + model.getValue() + "]");
			if (mapElement != null)
			{
				Element inElement = mapElement.element("RequestDataMap");
				model.setInputClass(inElement.attributeValue("importClass"));
				model.setInputList(initDataElement(inElement.elements()));

				Element outElement = mapElement.element("ResponseDataMap");
				model.setOutPutClass(outElement.attributeValue("importClass"));
				model.setOutputList(initDataElement(outElement.elements()));
			}
			entryMap.put(serviceElement.attributeValue("key"), model);
		}
		return entryMap;
	}

	private Map initDataElement(List eleList)
	{
		Map elementList = new HashMap();
		for (Iterator ite = eleList.iterator(); ite.hasNext();)
		{
			Element dataElement = (Element) ite.next();
			if (dataElement.getName().trim().toLowerCase()
					.equals("dataelement"))
			{
				DataElement dataEle = new DataElement();
				dataEle.setCharset(DataTransform.Trim(dataElement
						.attributeValue("charset")));
				dataEle.setDataName(DataTransform.Trim(dataElement
						.attributeValue("name")));
				dataEle.setIndex(DataTransform.Trim(dataElement
						.attributeValue("index")));
				dataEle.setLenght(DataTransform.Trim(dataElement
						.attributeValue("lenght")));
				dataEle.setSysat(DataTransform.Trim(dataElement
						.attributeValue("sysat")));
				dataEle.setDataValue(DataTransform.Trim(dataElement
						.attributeValue("value")));
				dataEle.setReadOnly(DataTransform.TransToBool(dataElement
						.attributeValue("readOnly")));
				dataEle.setDataType(DataTransform.Trim(dataElement
						.attributeValue("dataType")));
				elementList.put(DataTransform.TransToInt(dataElement
						.attributeValue("index")), dataEle);
			} else if (dataElement.getName().trim().toLowerCase().equals(
					"datalist"))
			{
				DataList dataList = new DataList();
				dataList.setImportClass(DataTransform.Trim(dataElement
						.attributeValue("importClass")));
				dataList.setCharset(DataTransform.Trim(dataElement
						.attributeValue("charset")));
				dataList.setDataName(DataTransform.Trim(dataElement
						.attributeValue("name")));
				dataList.setIndex(DataTransform.Trim(dataElement
						.attributeValue("index")));
				dataList.setLenght(DataTransform.Trim(dataElement
						.attributeValue("lenght")));
				dataList.setSysat(DataTransform.Trim(dataElement
						.attributeValue("sysat")));
				Map list = initDataElement(dataElement.elements());
				dataList.setElementList(list);
				elementList.put(DataTransform.TransToInt(dataElement
						.attributeValue("index")), dataList);
			}
		}
		return elementList;
	}

	private Element findServerByID(String strID) throws Exception
	{
		Document serDocument = XmlTools.loadXMLDocument(this.getConfFilePath()
				+ "/" + strID + ".xml");
		return serDocument.getRootElement().element("TCPIPService");
	}
}
