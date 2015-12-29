package biz.common.net.tcpClient;

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
import biz.common.net.tcp.CommProcessor;
import biz.common.net.tcp.TCPIPServerModel;
import biz.common.tools.DataTransform;
import biz.common.tools.XmlTools;

public class TCPClientServiceFactory {
	/**
	 * XML DOM Document 对象
	 */
	protected Document document;

	private Map services;

	private String confFilePath;

	public String getConfFilePath() {
		return confFilePath;
	}

	public void setConfFilePath(String confFilePath) {
		this.confFilePath = confFilePath;
	}

	public Map getServices() {
		return services;
	}

	public void setServices(Map services) {
		this.services = services;
	}

	public TCPClientServiceFactory() {
		this.setServices(new HashMap());
	}

	public void initializeServiceFactory(String fileName) {
		try {
			this.document = XmlTools.loadXMLDocument(fileName);
			initTCPIPService();
		} catch (Exception e) {
			BGMLogger.LogError("initialize serviceList is fail:"
					+ e.getMessage());
		}
	}

	public void initTCPIPService() {
		Element stylesElement = document.getRootElement();
		// TCPIPServerService节点解析
		List services = stylesElement.elements("TCPClient");
		for (Iterator ite = services.iterator(); ite.hasNext();) {
			Element serviceElement = (Element) ite.next();
			String strServiceName = serviceElement.attributeValue("id");
			TCPClient client = new TCPClient();
			Element infoElement = serviceElement.element("ClientInfo");

			String strHostName = DataTransform.Trim(infoElement
					.attribute("host"));
			client.setHostName(strHostName);

			String strType = DataTransform.Trim(infoElement.attribute("type"));
			client.setType(strType);

			int iPortNum = DataTransform.TransToInt(infoElement
					.attribute("port"));
			client.setPortNum(iPortNum);

			boolean keepAlive = DataTransform.TransToBool(infoElement
					.attribute("keepAlive"));
			client.setKeepAlive(keepAlive);

			Class objClass;
			Element formatEle = serviceElement.element("FormatStyle");
			String strCommFormatClass = DataTransform.Trim(formatEle
					.attributeValue("class"));
			try {
				objClass = Class.forName(strCommFormatClass);
				Object objDao = objClass.getConstructor().newInstance();
				client.setCommFormat((CommFormat) objDao);
			} catch (Exception e) {
				BGMLogger.LogInfo("load CommFormat class is fail:"
						+ e.getMessage());
			}
			Element comProEle = serviceElement.element("CommProcessor");
			String strCommProcessor = comProEle.getText().trim();
			try
			{
				objClass = Class.forName(strCommProcessor);
				Object objDao = objClass.getConstructor().newInstance();
				client.setCommProcessor((CommProcessor) objDao);
			} catch (Exception e)
			{
				BGMLogger.LogInfo("load CommProcessor class is fail:"
						+ e.getMessage());
			}

			try {
				Map servceMap = initServiceList(stylesElement, strServiceName);
				client.setServices(servceMap);
			} catch (Exception ex) {
				BGMLogger
						.LogError("Init TCPClient ID[" + strServiceName
								+ "] port[" + iPortNum + "] is fail:"
								+ ex.getMessage());
			}

			BGMLogger.LogInfo("Init TCPClient ID=[" + strServiceName + "]"
					+ " host=[" + strHostName + "]" + " type=[" + strType + "]"
					+ " port[" + iPortNum + "]");
			this.services.put(strServiceName, client);
		}
	}

	public Map initServiceList(Element stylesElement, String strServiceName)
			throws Exception {
		// TCPIPServiceDef节点解析
		Map servceMap = new HashMap();
		List serviceDefList = stylesElement.elements("TCPIPServiceDef");
		for (Iterator ite = serviceDefList.iterator(); ite.hasNext();) {
			Element serviceElement = (Element) ite.next();
			if (serviceElement.attributeValue("ServiceID").toString().trim()
					.equals(strServiceName)) {
				List serviceMap = serviceElement.elements("MapEntry");
				servceMap.put(strServiceName, initMapEntry(serviceMap));
			}
		}
		return servceMap;
	}

	public Map initMapEntry(List serviceMap) throws Exception {
		Map entryMap = new HashMap();
		for (Iterator ite = serviceMap.iterator(); ite.hasNext();) {
			Element serviceElement = (Element) ite.next();
			TCPIPServerModel model = new TCPIPServerModel();
			model.setBeanName(serviceElement.attributeValue("BeanName"));
			model.setKey(serviceElement.attributeValue("key"));
			model.setValue(serviceElement.attributeValue("value"));
			model.setOpName(serviceElement.attributeValue("OpName"));
			Element mapElement = findServerByID(serviceElement
					.attributeValue("value"));
			BGMLogger.LogInfo("TCPIPClient Seivece key[" + model.getKey()
					+ "] value[" + model.getValue() + "]");
			if (mapElement != null) {
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

	private Map initDataElement(List eleList) {
		Map elementList = new HashMap();
		for (Iterator ite = eleList.iterator(); ite.hasNext();) {
			Element dataElement = (Element) ite.next();
			if (dataElement.getName().trim().toLowerCase()
					.equals("dataelement")) {
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
					"datalist")) {
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

	private Element findServerByID(String strID) throws Exception {
		Document serDocument = XmlTools.loadXMLDocument(this.getConfFilePath()
				+ "/" + strID + ".xml");
		return serDocument.getRootElement().element("TCPIPService");
	}
}
