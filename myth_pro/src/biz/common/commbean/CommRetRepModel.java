package biz.common.commbean;

/**
 * 
 * @author ���޾�
 * 20100407
 * ͨ�û�Ӧ���ģ�CMT910��
 *
 */
public class CommRetRepModel extends PubReturnCode {

	//���ڵ���� 12x M SBN
	private String nodeNo;
	
	//�ύ���� 8n M SD0
	private String submitDate;
	
	//��ˮ��  �������Ҷ�����0 16n M CLZ
	private String flowNo;
	
	//�ύʱ�� 6n M RTT
	private String submitTime;
	
	//�������� 8n O ������������ķ��أ�����ҵ����Ҫ���㣬����ֶ�Ϊ���� WD0
	private String reckoningDate;
	
	//���㳡�� 2n O ������Ϊ�������ķ��أ�����������Ϊ��Ҫ����ı���ʱ������Ϊ���㳡�� CCH
	private String reckoningBatch;
	
	//ԭҵ����� 3n M ԭ����ҵ���CMT����ͬʱ�������ñ���ͷ��CMT��չ���������ͬ OYN
	private String souBusNo;
	
	//ԭҵ���ύ���� 8n M 051
	private String souBusSubDate;
	
	//ԭҵ����ڵ� 12x M ����Ǳ������Ŀ���û�з���ڵ㣨ί�нڵ�ȣ���Ϊԭ���ı���ͷ�е�Դ��ַ OTN
	private String souBusStartPoint;
	
	//ԭҵ����ˮ/��������ID ��PID�� 20x M ���Ϊԭҵ��������Ϊԭ����ID OLZ
	private String batchId;
	
	//����� 4n M  C42
	private String resultNo;
	
	//��ע 128g O ����˵����Ϣ  72A
	private String remark;
	
	//������Ϣ 40x O �ɸ����ر������ж��壨Ŀǰ�ޣ� INA
	private String assistantMsg;
	
	//�������� 1x O �ɸ����ر������ж��壨Ŀǰ�ޣ� FLA
	private String assistantFlag;
	
	//���ҵ���� 14n O ԭҵ��Ϊʵʱ�ۿ�ʱ�ý��Ϊʵ�۽�� AMT
    //ԭҵ��Ϊ�ʺ���֤��Ҫ�󷵻����ʱ�ý��Ϊ�ʺ����
	private String busAmt;
	
	//ԭʼƾ֤�� 30x O ���ԭҵ����ƾ֤�š�ԭʼƾ֤������ֶ�Ϊ����Ϊԭҵ��ĸ��ֶ� OVN
	//201��OVN   100���ĵ�21A
	private String souVoucherNo;
	
	//ԭҵ���ļ��� 80g O ���ԭҵ�����и����ļ���ҵ��OFNΪԭҵ���FLN or CLN��Ӧ�����ļ����� OFN
	private String souBusFileName;
	
	//����ǩ�� 256x O ��Ϊ201 211 301 311 241341�ķ��ر���ʱ���ֶ�Ϊ���� SIG
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
