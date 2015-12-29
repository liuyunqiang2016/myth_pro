package biz.common.format.charsplit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import biz.common.BGMLogger;
import biz.common.IModel;
import biz.common.data.webservice.BaseElement;
import biz.common.data.webservice.DataElement;
import biz.common.data.webservice.DataList;
import biz.common.format.AbsCommFormat;
import biz.common.format.CommFormat;
import biz.common.tools.DataTransform;

import com.viatt.zhjtpro.common.CryptionData;

public class CharSplitFormat extends AbsCommFormat implements CommFormat {

	public String getTransCode(byte[] msg) {
		String strMsg = new String(msg);
		System.out.println("getTransCode===="+strMsg);
		// try {
		// CryptionData DecodeStr = new CryptionData();
		// strMsg = DecodeStr.DecryptionStringData(strMsg);
		// } catch (Exception ex) {
		// return null;
		// }
		// String[] strList = strMsg.split("\\|");
		// if (strList.length > 0) {
		// return strList[1];
		// } else {
		// return null;
		// }
		Document doc = null;
		String msgId = null;
		try {
			doc = DocumentHelper.parseText(strMsg); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点xml
			msgId = rootElt.elementTextTrim("msgId");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgId;
	}

	public IModel newPackageReceived(byte[] msg, String strClassName,
			Map dataElements) throws Exception {
		String strMsg = new String(msg);
		BGMLogger.LogInfo("newPackageReceived():[" + strMsg + "]");
		System.out.println("newPackageReceived==================="+strMsg);
		// 为输入对象赋值
		Class classType = Class.forName(strClassName);
		Object obj = classType.getConstructor().newInstance();
		Document doc = null;
		try{
			doc = DocumentHelper.parseText(strMsg); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点xml
			for (Iterator ite = dataElements.entrySet().iterator(); ite.hasNext();) {
				BaseElement dataElement = null;
				Map.Entry entry = (Map.Entry) ite.next();
				dataElement = (DataElement) entry.getValue();
				String fieldName = dataElement.getDataName().trim();
				String value = rootElt.elementTextTrim(fieldName);
				
				String stringLetter = fieldName.substring(0, 1).toUpperCase();
				// 获得对应属性的set方法
				String setName = "set" + stringLetter + fieldName.substring(1);
				Field field = classType.getDeclaredField(fieldName);
				Method setMethod = classType.getMethod(setName,
						new Class[] { field.getType() });
				if (field.getType().getName().equals("double")) {
					setMethod.invoke(obj, new Object[] { DataTransform
							.TransToDouble(value) });
				} else {
					setMethod.invoke(obj, new Object[] { value });
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return (IModel) obj;
		// try {
		// CryptionData DecodeStr = new CryptionData();
		// strMsg = DecodeStr.DecryptionStringData(strMsg);
		// BGMLogger.LogInfo("newPackageReceived():[" + strMsg + "]");
		// } catch (Exception ex) {
		// throw new Exception("DecryptionStringData() [" + ex.getMessage()
		// + "] !");
		// }
//		String[] strSplitChars = this.getSplitChar().split(" ");
//		String[] strList = strMsg.split("\\" + strSplitChars[0]);
		// 为输入对象赋值
//		Class classType = Class.forName(strClassName);
//		Object obj = classType.getConstructor().newInstance();
		// 获得输入对象的所有属性,并赋值
//		for (Iterator ite = dataElements.entrySet().iterator(); ite.hasNext();) {
//			BaseElement dataElement = null;
//			try {
//				Map.Entry entry = (Map.Entry) ite.next();
//				dataElement = (DataElement) entry.getValue();
//			} catch (Exception e) {
//			}
//			String fieldName = dataElement.getDataName().trim();
//
//			int iIdex = biz.common.tools.DataTransform.TransToInt(dataElement
//					.getIndex());
//
//			String value = strList[iIdex].toString();
//			// System.out.println(iIdex+" "+value);
//			String stringLetter = fieldName.substring(0, 1).toUpperCase();
//			// 获得对应属性的set方法
//			String setName = "set" + stringLetter + fieldName.substring(1);
//			try {
//				;
//				Field field = classType.getDeclaredField(fieldName);
//				Method setMethod = classType.getMethod(setName,
//						new Class[] { field.getType() });
//				if (field.getType().getName().equals("double")) {
//					setMethod.invoke(obj, new Object[] { DataTransform
//							.TransToDouble(value) });
//				} else {
//					setMethod.invoke(obj, new Object[] { value });
//				}
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				throw new Exception("field [" + fieldName + "] not found!");
//			}
//		}
//		return (IModel) obj;
	}

	public byte[] getPackageSend(IModel model, String strClassName,
			Map dataElements) throws Exception {
		String strOut = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml>";
		// 获得输入对象的所有属性,并赋值
		if (dataElements != null && dataElements.size() > 0) {
			String[] strSplitChars = this.getSplitChar().split(" ");
			String[] strList = new String[dataElements.size()];
			for (Iterator ite = dataElements.entrySet().iterator(); ite
					.hasNext();) {
				BaseElement dataElement = null;
				try {
					Map.Entry entry = (Map.Entry) ite.next();
					String className = entry.getValue().getClass().getName();
					int idx = className.lastIndexOf(".");
					className = className.substring(idx + 1);
					if (className.equals("DataElement")) {
						dataElement = (DataElement) entry.getValue();
					} else {
						dataElement = (DataList) entry.getValue();
					}
				} catch (Exception e) {

				}
				String fieldName = dataElement.getDataName();
				String stringLetter = fieldName.substring(0, 1).toUpperCase();
				// 获得对应属性的get方法
				String getName = "get" + stringLetter + fieldName.substring(1);
				Method getMethod = model.getClass().getMethod(getName);
				// 执行对象对应属性的get方法,获取该对象的值
				Object value = getMethod.invoke(model, new Object[] {});
				if(value==null){
					value="";
				}
				strOut += "<"+fieldName+">"+value+"</"+fieldName+">";
				
//				int idx = DataTransform.TransToInt(dataElement.getIndex());
//				if (value == null) {
//					strList[idx] = "";
//				} else {
//					if (dataElement instanceof DataElement) {
//						strList[idx] = value.toString();
//					} else if (dataElement instanceof DataList) {
//						Map models = (Map) value;
//						strList[idx] = "";
//						Map ikColl = ((DataList) dataElement).getElementList();
//						for (Iterator mol = models.entrySet().iterator(); mol
//								.hasNext();) {
//							Map.Entry entry = (Map.Entry) mol.next();
//							Object modelList = entry.getValue();
//							String[] ikList = new String[ikColl.size()];
//							String liststr = "";
//							for (Iterator ikc = ikColl.entrySet().iterator(); ikc
//									.hasNext();) {
//								Map.Entry ikEntry = (Map.Entry) ikc.next();
//								DataElement dataElement2 = (DataElement) ikEntry
//										.getValue();
//								String fieldName2 = dataElement2.getDataName();
//								String stringLetter2 = fieldName2.substring(0,
//										1).toUpperCase();
//								String getName2 = "get" + stringLetter2
//										+ fieldName2.substring(1);
//								Method getMethod2 = modelList.getClass()
//										.getMethod(getName2);
//								Object value2 = getMethod2.invoke(modelList,
//										new Object[] {});
//								int idx2 = DataTransform
//										.TransToInt(dataElement2.getIndex());
//								if (value2 == null) {
//									ikList[idx2] = "";
//								} else {
//									ikList[idx2] = value2.toString();
//								}
//							}
//							for (int i = 0; i < ikList.length; i++) {
//								liststr += ikList[i] + strSplitChars[1];
//							}
//							strList[idx] += liststr + strSplitChars[2];
//						}
//					}
//				}
			}
//
//			for (int i = 0; i < strList.length; i++) {
//				strOut += strList[i].toString() + strSplitChars[0];
//			}
		}
		strOut+="</xml>";
		System.out.println(strOut);
		BGMLogger.LogInfo("FORMAT():[" + strOut + "]");
//		CryptionData DecodeStr = new CryptionData();
//		if (!strOut.equals("")) {
//			strOut = DecodeStr.EncryptionStringData(strOut);
//			BGMLogger.LogInfo("FORMAT():[" + strOut + "]");
//		}
		return strOut.getBytes();
	}

	public String getTransCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
