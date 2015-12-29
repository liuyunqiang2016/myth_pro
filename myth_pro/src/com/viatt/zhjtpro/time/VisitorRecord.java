package com.viatt.zhjtpro.time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.viatt.zhjtpro.bo.TblVisitorDimBo;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.service.ITblVisitorDimService;

public class VisitorRecord {

	private static final Log log = LogFactory.getLog(VisitorRecord.class);

	public static void importRecord() {
		log.info("--run VisitorRecord---");
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
				if (filename.substring(0, 8).equals("visitor_")) {
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
		ITblVisitorDimService visitor = (ITblVisitorDimService) bean
				.getBean("tblVisitorDimService");
		BufferedReader in = new BufferedReader(inStr);
		try {
			String strReadLine = in.readLine(); // 一次读取一行
			//来访时间，门禁模块编号，房号，通话时长，访客图片（待定）
			while (strReadLine != null) // 判断是否有抓到资料
			{
				String str[] = strReadLine.split("\\|");
				String createTime = str[0];
				String equCode = str[1];
				String roomCode = str[2];
				String comuTime = str[3];
				String comuImg = str[4];
				String comuImg1 = str[5];
				String comuImg2 = str[6];
				String comuImg3 = str[7];
				String comuImg4 = str[8];
				
				TblVisitorDimBo bo = new TblVisitorDimBo();
				bo.setComuTime(comuTime);
				bo.setEquCode(equCode);
				bo.setRoomCode(roomCode);
				bo.setVisiImg(comuImg);
				bo.setVisiImg1(comuImg1);
				bo.setVisiImg2(comuImg2);
				bo.setVisiImg3(comuImg3);
				bo.setVisiImg4(comuImg4);
				bo.setVisiTime(createTime);
				visitor.save(bo);

				
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
