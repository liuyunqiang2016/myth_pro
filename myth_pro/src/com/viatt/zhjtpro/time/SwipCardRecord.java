package com.viatt.zhjtpro.time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.viatt.zhjtpro.bo.TblContrDimBo;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.service.ITblContrDimService;

public class SwipCardRecord {
	private static final Log log = LogFactory.getLog(SwipCardRecord.class);

	public static void importRecord() {
		log.info("--run SwipCardRecord---");
		String ftpDir = PropertiesReader.getProperty("ftpDir");
		if (ftpDir == null || ftpDir == "") {
			log.info("FTP路径未配置");
			return;
		}
		String swipDir = ftpDir;
		String swipDirBak = ftpDir + "/upfileBak/";
		File dir = new File(swipDir);
		if (!dir.isDirectory()) {
			return;
		}
		if (!dir.exists()) {
			dir.mkdirs();
		}
		WebApplicationContext bean = StartupServlet.bean;
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				String filename = files[i].getName();
				if (filename.substring(0, 5).equals("card_")) {
					parseTxt(files[i], bean);
					files[i].renameTo(new File(swipDirBak + filename));
				}
			}
		}
	}

	public static void parseTxt(File file, WebApplicationContext bean) {
		InputStreamReader inStr = null;
		InputStream instream = null;
		try {
			instream = new FileInputStream(file);
			inStr = new InputStreamReader(instream, "GBK");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return;
		}
		ITblContrDimService service = (ITblContrDimService) bean
				.getBean("tblContrDimService");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BufferedReader in = new BufferedReader(inStr);
		try {
			String strReadLine = in.readLine(); // 一次读取一行
			//门禁卡编号，刷卡时间，门禁模块编号，摄像图片（待定）
			while (strReadLine != null) // 判断是否有抓到资料
			{
				//strReadLine---> 236273344|20140422170646|010001 
				String str[] = strReadLine.split("\\|");
				String cardNo = str[0];//门禁卡编号
				String createTime = str[1];//刷卡时间
				String equCode = str[2];//门禁模块编号
//				String exp1 = str[3];//摄像图片     NUll Exception  
				
				Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(createTime);
				
				TblContrDimBo bo = new TblContrDimBo();
				bo.setCardNo(cardNo);
				bo.setCreateTime(sf.format(date));
				bo.setEquCode(equCode);
//				bo.setExp1(exp1);
				bo.setOpType("1");//门禁卡
				service.save(bo);
				strReadLine = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			instream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
