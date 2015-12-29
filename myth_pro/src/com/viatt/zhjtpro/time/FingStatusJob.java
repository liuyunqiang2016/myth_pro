package com.viatt.zhjtpro.time;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.viatt.zhjtpro.bo.TblAdDimBo;
import com.viatt.zhjtpro.bo.TblEquDimBo;
import com.viatt.zhjtpro.bo.TblFingStatusDimBo;
import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblNoticeDimBo;
import com.viatt.zhjtpro.bo.TblParamDimBo;
import com.viatt.zhjtpro.bo.TblServiceDimBo;
import com.viatt.zhjtpro.common.PropertiesReader;
import com.viatt.zhjtpro.common.Smsclient;
import com.viatt.zhjtpro.service.ITblEquDimService;
import com.viatt.zhjtpro.service.ITblFingDimService;
import com.viatt.zhjtpro.service.ITblFingStatusDimService;
import com.viatt.zhjtpro.service.ITblParamDimService;
import com.viatt.zhjtpro.service.ITblServiceDimService;

public class FingStatusJob {

	private static final Log log = LogFactory.getLog(FingStatusJob.class);
	public static void sendMsg() {
		log.info("--run FingStatusJob---");
		WebApplicationContext bean = StartupServlet.bean;
		ITblFingStatusDimService fingStatus = (ITblFingStatusDimService) bean
				.getBean("tblFingStatusDimService");
		ITblEquDimService equ = (ITblEquDimService)bean
				.getBean("tblEquDimService");
		ITblServiceDimService service = (ITblServiceDimService)bean
			.getBean("tblServiceDimService");
		ITblParamDimService param = (ITblParamDimService)bean
			.getBean("tblParamDimService");
		ITblFingDimService fingDimService = (ITblFingDimService)bean.getBean("tblFingDimService");
		TblParamDimBo parambo = param.findTblParamDimById("100001");
		if(parambo==null){
			log.info("�豸�˿ڲ���δ����");
			return;
		}
		String port = parambo.getParamValue();
		try {
			List list = fingStatus.findTblFingStatusDimByWhere(" where sendzt='0'");
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					TblFingStatusDimBo bo = (TblFingStatusDimBo)list.get(i);
					TblEquDimBo equbo = equ.findTblEquDimById(bo.getEquCode());
					//�豸�����ڣ���ɾ����Ϣ���и��豸�ŵ�����
					if(equbo==null){
						fingStatus.deleteList(" where equ_code='"+bo.getEquCode()+"'");
						continue;
					}
					//����豸״̬�Ƿ�����
					if(equbo.getEquState()!=null && equbo.getEquState().equals("0")){
						continue;
					}
					
					String updType = bo.getOpType() ;
					
					// ����ָ��֪ͨ����
					String sndbuf = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><msgId>I0009</msgId><cardNo>"
							+ bo.getUsrNo()
							+ "</cardNo><fing1>"
							+ bo.getFingImg1()
							+ "</fing1><fing2>"
							+ bo.getFingImg2()
							+ "</fing2><updType>"+ updType+"</updType></xml>";
					sndbuf = String.format("%06d", sndbuf.getBytes().length) + sndbuf;
					if (equ != null) {
						try {
							//����ָ����Ϣ
							Smsclient.SmsClient(equbo.getIpAdd(), port, sndbuf, 6);
							bo.setSendZt("1");  //�޸ķ���״̬Ϊ�ɹ�
							fingStatus.save(bo);
						} catch (Exception e) {
							e.printStackTrace();
							continue ;
						} 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
