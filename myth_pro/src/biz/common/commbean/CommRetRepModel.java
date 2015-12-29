package biz.common.commbean;

/**
 * 
 * @author 翟艳军
 * 20100407
 * 通用回应报文（CMT910）
 *
 */
public class CommRetRepModel extends PubReturnCode {

	//发节点编码 12x M SBN
	private String nodeNo;
	
	//提交日期 8n M SD0
	private String submitDate;
	
	//流水号  定长，右对齐左补0 16n M CLZ
	private String flowNo;
	
	//提交时间 6n M RTT
	private String submitTime;
	
	//清算日期 8n O 如果是联网中心返回，并且业务需要清算，则该字段为必须 WD0
	private String reckoningDate;
	
	//清算场次 2n O 当报文为联网中心返回，而且请求报文为需要清算的报文时，该域为清算场次 CCH
	private String reckoningBatch;
	
	//原业务代码 3n M 原请求业务的CMT编码同时必须设置报文头的CMT扩展码跟该域相同 OYN
	private String souBusNo;
	
	//原业务提交日期 8n M 051
	private String souBusSubDate;
	
	//原业务发起节点 12x M 如果是报文正文块中没有发起节点（委托节点等）则为原报文报文头中的源地址 OTN
	private String souBusStartPoint;
	
	//原业务流水/或者批量ID （PID） 20x M 如果为原业务批量则为原批量ID OLZ
	private String batchId;
	
	//结果码 4n M  C42
	private String resultNo;
	
	//备注 128g O 附加说明信息  72A
	private String remark;
	
	//辅助信息 40x O 由各返回报文自行定义（目前无） INA
	private String assistantMsg;
	
	//辅助标致 1x O 由各返回报文自行定义（目前无） FLA
	private String assistantFlag;
	
	//相关业务金额 14n O 原业务为实时扣款时该金额为实扣金额 AMT
    //原业务为帐号验证并要求返回余额时该金额为帐号余额
	private String busAmt;
	
	//原始凭证号 30x O 如果原业务有凭证号、原始凭证号则该字段为必须为原业务的该字段 OVN
	//201的OVN   100报文的21A
	private String souVoucherNo;
	
	//原业务文件名 80g O 如果原业务是有附加文件的业务OFN为原业务的FLN or CLN（应保存文件名） OFN
	private String souBusFileName;
	
	//数字签名 256x O 当为201 211 301 311 241341的返回报文时该字段为必须 SIG
	private String signNumber;

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
