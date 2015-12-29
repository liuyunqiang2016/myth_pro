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

public class FingRecord {

	private static final Log log = LogFactory.getLog(FingRecord.class);

	public static void importRecord() {
		log.info("--run FingRecord---");
		String ftpDir = PropertiesReader.getProperty("ftpDir");
		if (ftpDir == null || ftpDir == "") {
			log.info("FTP路径未配置");
			return;
		}
		String swipDir = ftpDir ;
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
				if (filename.substring(0, 5).equals("fing_")) {
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
		BufferedReader in = new BufferedReader(inStr);
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			String strReadLine = in.readLine(); // 一次读取一行
			//门禁模块编号，身份证号码，刷卡时间
			while (strReadLine != null) // 判断是否有抓到资料
			{
				String str[] = strReadLine.split("\\|");
				String equCode = str[0];
				String userNo = str[1];
				String createTime = str[2];
				
				TblContrDimBo bo = new TblContrDimBo();
				bo.setCardNo(userNo);
				bo.setCreateTime(sf.format(date.parse(createTime)));
				bo.setEquCode(equCode);
				bo.setExp1("");
				bo.setOpType("2");//指纹
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
