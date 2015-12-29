package com.viatt.zhjtpro.time;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.service.ITblAdDimService;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblFingDimService;
import com.viatt.zhjtpro.service.ITblInfoStatusDimService;
import com.viatt.zhjtpro.service.ITblNoticeDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;
import com.viatt.zhjtpro.service.ITblServiceDimService;

public class InfoStatusJob {

	private static final Log log = LogFactory.getLog(InfoStatusJob.class);
	public static void sendMsg() {
		log.info("--run InfoStatusJob---");
		WebApplicationContext bean = StartupServlet.bean;
		ITblInfoStatusDimService infostatus = (ITblInfoStatusDimService) bean
				.getBean("tblInfoStatusDimService");
		ITblNoticeDimService notice = (ITblNoticeDimService)bean
				.getBean("tblNoticeDimService");
		ITblEquDimService equ = (ITblEquDimService)bean
				.getBean("tblEquDimService");
		ITblServiceDimService service = (ITblServiceDimService)bean
			.getBean("tblServiceDimService");
		ITblAdDimService ad = (ITblAdDimService)bean
			.getBean("tblAdDimService");
		ITblParamDimService param = (ITblParamDimService)bean
			.getBean("tblParamDimService");
		ITblFingDimService fingDimService = (ITblFingDimService)bean.getBean("tblFingDimService");
		TblParamDimBo parambo = param.findTblParamDimById("100001");
		if(parambo==null){
			log.info("设备端口参数未配置");
			return;
		}
		String port = parambo.getParamValue();
		try {
			List list = infostatus.findTblInfoStatusDimByWhere(" where sendzt='0'");
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					TblInfoStatusDimBo bo = (TblInfoStatusDimBo)list.get(i);
					TblEquDimBo equbo = equ.findTblEquDimById(bo.getEquCode());
					//设备不存在，则删除信息表中该设备号的数据
					if(equbo==null){
						infostatus.deleteList(" where equ_code='"+bo.getEquCode()+"'");
						continue;
					}
					//检测设备状态是否在线
					if(equbo.getEquState()!=null && equbo.getEquState().equals("0")){
						continue;
					}
					//公告信息
					if(bo.getInfoType().equals("n")){
						String snd="";
						TblNoticeDimBo nbo = notice.findTblNoticeDimById(bo.getInfoCode());
						if(nbo==null){
							snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
								+bo.getInfoCode()+"</auunId><updType>2</updType><auunTitle></auunTitle><context>"
								+"</context><creTime>"
								+"</creTime><creAuthor>"
								+"</creAuthor><type>0</type></xml>";
						}else{
						snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
							+bo.getInfoCode()+"</auunId><updType>"+
							bo.getUpdType()+"</updType><auunTitle>"
							+nbo.getTitile()+"</auunTitle><context>"
							+nbo.getContext()+"</context><creTime>"
							+nbo.getCreateTime()+"</creTime><creAuthor>"
							+nbo.getCreateUsr()+"</creAuthor><type>0</type></xml>";
						}
						try{
							snd = String.format("%06d", snd.getBytes().length)+ snd;
							Smsclient.SmsClient(equbo.getIpAdd(), port, snd, 6);
							bo.setSendZt("1");
							infostatus.save(bo);
						}catch (Exception e1) {
							e1.printStackTrace();
							continue;
						}
					}else if(bo.getInfoType().equals("s")){
						TblServiceDimBo sbo = service.findTblServiceDimById(bo.getRemark());
						String snd="<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0008</msgId><serId>"
							+sbo.getSerId()+"</serId><serType>"
							+sbo.getType()+"</serType><chkContext>"
							+sbo.getChkRes()+"</chkContext><chkTime>"
							+sbo.getChkTime()+"</chkTime><chkUser>"
							+sbo.getChkUsr()+"</chkUser></xml>";
						try{
							snd = String.format("%06d", snd.getBytes().length)+ snd;
							Smsclient.SmsClient(equbo.getIpAdd(), port, snd, 6);
							bo.setSendZt("1");
							infostatus.save(bo);
						}catch (Exception e2) {
							e2.printStackTrace();
							continue;
						}
					}else if(bo.getInfoType().equals("a")){
						String snd="";
						String upDir = PropertiesReader.getProperty("upDir");
						TblAdDimBo abo = ad.findTblAdDimById(bo.getInfoCode());
						if(abo==null){
							snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0006</msgId><advId>"
								+bo.getInfoCode()+"</advId><updType>2</updType><advTitle></advTitle><advContext>"
								+"</advContext><imgUrl1>"
								+"</imgUrl1><imgUrl2>"
								+"</imgUrl2><downUrl></downUrl><img1size>"
								+"</img1size><img2size>"
								+"</img2size><beginDate>"
								+"</beginDate><endDate>"
								+"</endDate></xml>";
						}else{
							snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0006</msgId><advId>"
								+abo.getAdId()+"</advId><updType>"
								+bo.getUpdType()+"</updType><advTitle>"
								+abo.getTitile()+"</advTitle><advContext>"
								+abo.getContext()+"</advContext><imgUrl1>"
								+abo.getBgImg()+"</imgUrl1><imgUrl2>"
								+abo.getSmImg()+"</imgUrl2><downUrl>"+upDir+"</downUrl><img1size>"
								+abo.getBgSize()+"</img1size><img2size>"
								+abo.getSmSize()+"</img2size><beginDate>"
								+abo.getBeginDate()+"</beginDate><endDate>"
								+abo.getEndDate()+"</endDate></xml>";
						}
						try{
							snd = String.format("%06d", snd.getBytes().length)+ snd;
							Smsclient.SmsClient(equbo.getIpAdd(), port, snd, 6);
							bo.setSendZt("1");
							infostatus.save(bo);
						}catch (Exception e3) {
							e3.printStackTrace();
							continue;
						}
					}else if(bo.getInfoType().equals("f")){
						//缴费信息
						String snd="";
						String mark = bo.getRemark();
						String s[] = mark.split("|");
						if(s!=null&&s.length==3){
							snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
								+bo.getInfoCode()+"</auunId><updType>"+
								bo.getUpdType()+"</updType><auunTitle>"
								+bo.getInfoTitle()+"</auunTitle><context>"
								+s[0]+"</context><creTime>"
								+s[1]+"</creTime><creAuthor>"
								+s[2]+"</creAuthor><type>1</type></xml>";
						}else{
							snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0001</msgId><auunId>"
								+bo.getInfoCode()+"</auunId><updType>2</updType><auunTitle></auunTitle><context>"
								+"</context><creTime>"
								+"</creTime><creAuthor>"
								+"</creAuthor><type>1</type></xml>";
						}
						try{
							snd = String.format("%06d", snd.getBytes().length)+ snd;
							Smsclient.SmsClient(equbo.getIpAdd(), port, snd, 6);
							bo.setSendZt("1");
							infostatus.save(bo);
						}catch (Exception e3) {
							e3.printStackTrace();
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
