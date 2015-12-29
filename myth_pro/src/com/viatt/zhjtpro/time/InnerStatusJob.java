package com.viatt.zhjtpro.time;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblInnerStatusDimBo;
import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblInnerStatusDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;

public class InnerStatusJob {

	private static final Log log = LogFactory.getLog(InnerStatusJob.class);
	public static void sendMsg() {
		log.info("--run InnerStatusJob---");
		WebApplicationContext bean = StartupServlet.bean;
		ITblInnerStatusDimService innerstatus = (ITblInnerStatusDimService) bean
				.getBean("tblInnerStatusDimService");
		ITblEquDimService equ = (ITblEquDimService)bean
				.getBean("tblEquDimService");
		ITblParamDimService param = (ITblParamDimService)bean
			.getBean("tblParamDimService");
		TblParamDimBo parambo = param.findTblParamDimById("100001");
		if(parambo==null){
			log.info("�豸�˿ڲ���δ����");
			return;
		}
		String port = parambo.getParamValue();
		try {
			List list = innerstatus.findTblInnerStatusDimByWhere(" where sendzt='0'");
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					TblInnerStatusDimBo bo = (TblInnerStatusDimBo)list.get(i);
					TblEquDimBo equbo = equ.findTblEquDimById(bo.getOuterid());
					//�豸�����ڣ���ɾ����Ϣ���и��豸�ŵ�����
					if(equbo==null){
						innerstatus.deleteList(" where outerid='"+bo.getOuterid()+"'");
						continue;
					}
					//����豸״̬�Ƿ�����
					if(equbo.getEquState()!=null && equbo.getEquState().equals("0")){
						continue;
					}
					//����Ȩ��
					if(bo.getOptype().equals("1")){
						String snd="";
						snd = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0011</msgId><outerId>"
							+bo.getOuterid()+"</outerId><innerId>"+
							bo.getInnerid()+"</innerId><perType>"
							+bo.getPertype()+"</perType></xml>";
						try{
							snd = String.format("%06d", snd.getBytes().length)+ snd;
							Smsclient.SmsClient(equbo.getIpAdd(), port, snd, 6);
							bo.setSendzt("1");
							innerstatus.save(bo);
						}catch (Exception e1) {
							e1.printStackTrace();
							continue;
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("--run innerstatus end---");
	}
}
