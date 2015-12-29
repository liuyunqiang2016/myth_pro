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
			log.info("FTP·��δ����");
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
			String strReadLine = in.readLine(); // һ�ζ�ȡһ��
			//�Ž�����ţ�ˢ��ʱ�䣬�Ž�ģ���ţ�����ͼƬ��������
			while (strReadLine != null) // �ж��Ƿ���ץ������
			{
				//strReadLine---> 236273344|20140422170646|010001 
				String str[] = strReadLine.split("\\|");
				String cardNo = str[0];//�Ž������
				String createTime = str[1];//ˢ��ʱ��
				String equCode = str[2];//�Ž�ģ����
//				String exp1 = str[3];//����ͼƬ     NUll Exception  
				
				Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(createTime);
				
				TblContrDimBo bo = new TblContrDimBo();
				bo.setCardNo(cardNo);
				bo.setCreateTime(sf.format(date));
				bo.setEquCode(equCode);
//				bo.setExp1(exp1);
				bo.setOpType("1");//�Ž���
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
