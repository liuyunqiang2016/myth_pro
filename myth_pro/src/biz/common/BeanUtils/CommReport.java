package biz.common.BeanUtils;

import biz.common.Excep.ReportParseException;
import biz.common.Excep.StringTransformException;

/**
 * 
 * @author 翟艳军 20100312
 * 通用回应报文处理
 * 对通用报文进行处理 包括通用报文的拼接和解析
 */
public class CommReport {
	
	//发节点编码 12x M
	private String nodeNo;
	
	//提交日期 8n M 
	private String submitDate;
	
	//流水号  定长，右对齐左补0 16n M
	private String flowNo;
	
	//提交时间 6n M
	private String submitTime;
	
	//清算日期 8n O 如果是联网中心返回，并且业务需要清算，则该字段为必须
	private String reckoningDate;
	
	//清算场次 2n O 当报文为联网中心返回，而且请求报文为需要清算的报文时，该域为清算场次
	private String reckoningBatch;
	
	//原业务代码 3n M 原请求业务的CMT编码同时必须设置报文头的CMT扩展码跟该域相同
	private String souBusNo;
	
	//原业务提交日期 8n M
	private String souBusSubDate;
	
	//原业务发起节点 12x M 如果是报文正文块中没有发起节点（委托节点等）则为原报文报文头中的源地址
	private String souBusStartPoint;
	
	//原业务流水/或者批量ID （PID） 20x M 如果为原业务批量则为原批量ID
	private String batchId;
	
	//结果码 4n M 
	private String resultNo;
	
	//备注 128g O 附加说明信息 
	private String remark;
	
	//辅助信息 40x O 由各返回报文自行定义（目前无）
	private String assistantMsg;
	
	//辅助标致 1x O 由各返回报文自行定义（目前无）
	private String assistantFlag;
	
	//相关业务金额 14n O 原业务为实时扣款时该金额为实扣金额
    //原业务为帐号验证并要求返回余额时该金额为帐号余额
	private String busAmt;
	
	//原始凭证号 30x O 如果原业务有凭证号、原始凭证号则该字段为必须为原业务的该字段
	//201的OVN   100报文的21A
	private String souVoucherNo;
	
	//原业务文件名 80g O 如果原业务是有附加文件的业务OFN为原业务的FLN or CLN（应保存文件名）
	private String souBusFileName;
	
	//数字签名 256x O 当为201 211 301 311 241341的返回报文时该字段为必须
	private String signNumber;

	/**
	 * 无参构造方法
	 *
	 */
	public CommReport(){
		
	}
	
	/**
	 * 所有参数构造方法
	 * @param nodeNo
	 * @param submitDate
	 * @param flowNo
	 * @param submitTime
	 * @param reckoningDate
	 * @param reckoningBatch
	 * @param souBusNo
	 * @param souBusSubDate
	 * @param souBusStartPoint
	 * @param batchId
	 * @param resultNo
	 * @param remark
	 * @param assistantMsg
	 * @param assistantFlag
	 * @param busAmt
	 * @param souVoucherNo
	 * @param souBusFileName
	 * @param signNumber
	 */
	public CommReport(String nodeNo, String submitDate, 
			String flowNo, String submitTime, String reckoningDate, 
			String reckoningBatch, String souBusNo, String souBusSubDate, 
			String souBusStartPoint, String batchId, String resultNo, 
			String remark, String assistantMsg, String assistantFlag, 
			String busAmt, String souVoucherNo, String souBusFileName, 
			String signNumber) {
		super();
		this.nodeNo = nodeNo;
		this.submitDate = submitDate;
		this.flowNo = flowNo;
		this.submitTime = submitTime;
		this.reckoningDate = reckoningDate;
		this.reckoningBatch = reckoningBatch;
		this.souBusNo = souBusNo;
		this.souBusSubDate = souBusSubDate;
		this.souBusStartPoint = souBusStartPoint;
		this.batchId = batchId;
		this.resultNo = resultNo;
		this.remark = remark;
		this.assistantMsg = assistantMsg;
		this.assistantFlag = assistantFlag;
		this.busAmt = busAmt;
		this.souVoucherNo = souVoucherNo;
		this.souBusFileName = souBusFileName;
		this.signNumber = signNumber;
	}

	/**
	 * 
	 * @param sourceStr 得到的返回报文源码
	 * @return 解析后的报文类
	 * @throws ReportParseException 通用返回报文解析异常类
	 */
	public CommReport parseCommReport(String sourceStr)throws ReportParseException{
		CommReport crr = new CommReport();
		try{
			crr.setNodeNo(sourceStr.substring(0, 12));
			crr.setSubmitDate(sourceStr.substring(12, 20));
			crr.setFlowNo(sourceStr.substring(20, 36));
			crr.setSubmitTime(sourceStr.substring(36, 42));
			crr.setReckoningDate(sourceStr.substring(42, 50));
			crr.setReckoningBatch(sourceStr.substring(50, 52));
			crr.setSouBusNo(sourceStr.substring(52, 55));
			crr.setSouBusSubDate(sourceStr.substring(55, 63));
			crr.setSouBusStartPoint(sourceStr.substring(63, 75));
			crr.setBatchId(sourceStr.substring(75, 95));
			crr.setResultNo(sourceStr.substring(95, 99));
			crr.setRemark(sourceStr.substring(99, 227));
			crr.setAssistantMsg(sourceStr.substring(227, 260));
			crr.setAssistantFlag(sourceStr.substring(260, 261));
			crr.setBusAmt(sourceStr.substring(261, 275));
			crr.setSouVoucherNo(sourceStr.substring(275, 305));
			crr.setSouBusFileName(sourceStr.substring(305, 385));
			crr.setSignNumber(sourceStr.substring(385, 641));
		}catch(Exception e){
			throw new  ReportParseException();
		}
		return crr;
	}
	
	/**
	 * 
	 * @param crr 报文对象参数
	 * @return 返回报文
	 * @throws StringTransformException 字符串转换异常
	 */
	public String concatCommReport(CommReport crr)throws StringTransformException{
		String targetStr ="";
			try {
				targetStr+=StringTransform.changeString(crr.getAssistantFlag());
			} catch (StringTransformException e) {
				throw new StringTransformException();
			}
		return null;
	}
	
	
	public String getAssistantFlag() {
		return assistantFlag;
	}

	public void setAssistantFlag(String assistantFlag) {
		this.assistantFlag = assistantFlag;
	}

	public String getAssistantMsg() {
		return assistantMsg;
	}

	public void setAssistantMsg(String assistantMsg) {
		this.assistantMsg = assistantMsg;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBusAmt() {
		return busAmt;
	}

	public void setBusAmt(String busAmt) {
		this.busAmt = busAmt;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public String getReckoningBatch() {
		return reckoningBatch;
	}

	public void setReckoningBatch(String reckoningBatch) {
		this.reckoningBatch = reckoningBatch;
	}

	public String getReckoningDate() {
		return reckoningDate;
	}

	public void setReckoningDate(String reckoningDate) {
		this.reckoningDate = reckoningDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResultNo() {
		return resultNo;
	}

	public void setResultNo(String resultNo) {
		this.resultNo = resultNo;
	}

	public String getSignNumber() {
		return signNumber;
	}

	public void setSignNumber(String signNumber) {
		this.signNumber = signNumber;
	}

	public String getSouBusFileName() {
		return souBusFileName;
	}

	public void setSouBusFileName(String souBusFileName) {
		this.souBusFileName = souBusFileName;
	}

	public String getSouBusNo() {
		return souBusNo;
	}

	public void setSouBusNo(String souBusNo) {
		this.souBusNo = souBusNo;
	}

	public String getSouBusStartPoint() {
		return souBusStartPoint;
	}

	public void setSouBusStartPoint(String souBusStartPoint) {
		this.souBusStartPoint = souBusStartPoint;
	}

	public String getSouBusSubDate() {
		return souBusSubDate;
	}

	public void setSouBusSubDate(String souBusSubDate) {
		this.souBusSubDate = souBusSubDate;
	}

	public String getSouVoucherNo() {
		return souVoucherNo;
	}

	public void setSouVoucherNo(String souVoucherNo) {
		this.souVoucherNo = souVoucherNo;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	
}
