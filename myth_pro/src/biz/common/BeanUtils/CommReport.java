package biz.common.BeanUtils;

import biz.common.Excep.ReportParseException;
import biz.common.Excep.StringTransformException;

/**
 * 
 * @author ���޾� 20100312
 * ͨ�û�Ӧ���Ĵ���
 * ��ͨ�ñ��Ľ��д��� ����ͨ�ñ��ĵ�ƴ�Ӻͽ���
 */
public class CommReport {
	
	//���ڵ���� 12x M
	private String nodeNo;
	
	//�ύ���� 8n M 
	private String submitDate;
	
	//��ˮ��  �������Ҷ�����0 16n M
	private String flowNo;
	
	//�ύʱ�� 6n M
	private String submitTime;
	
	//�������� 8n O ������������ķ��أ�����ҵ����Ҫ���㣬����ֶ�Ϊ����
	private String reckoningDate;
	
	//���㳡�� 2n O ������Ϊ�������ķ��أ�����������Ϊ��Ҫ����ı���ʱ������Ϊ���㳡��
	private String reckoningBatch;
	
	//ԭҵ����� 3n M ԭ����ҵ���CMT����ͬʱ�������ñ���ͷ��CMT��չ���������ͬ
	private String souBusNo;
	
	//ԭҵ���ύ���� 8n M
	private String souBusSubDate;
	
	//ԭҵ����ڵ� 12x M ����Ǳ������Ŀ���û�з���ڵ㣨ί�нڵ�ȣ���Ϊԭ���ı���ͷ�е�Դ��ַ
	private String souBusStartPoint;
	
	//ԭҵ����ˮ/��������ID ��PID�� 20x M ���Ϊԭҵ��������Ϊԭ����ID
	private String batchId;
	
	//����� 4n M 
	private String resultNo;
	
	//��ע 128g O ����˵����Ϣ 
	private String remark;
	
	//������Ϣ 40x O �ɸ����ر������ж��壨Ŀǰ�ޣ�
	private String assistantMsg;
	
	//�������� 1x O �ɸ����ر������ж��壨Ŀǰ�ޣ�
	private String assistantFlag;
	
	//���ҵ���� 14n O ԭҵ��Ϊʵʱ�ۿ�ʱ�ý��Ϊʵ�۽��
    //ԭҵ��Ϊ�ʺ���֤��Ҫ�󷵻����ʱ�ý��Ϊ�ʺ����
	private String busAmt;
	
	//ԭʼƾ֤�� 30x O ���ԭҵ����ƾ֤�š�ԭʼƾ֤������ֶ�Ϊ����Ϊԭҵ��ĸ��ֶ�
	//201��OVN   100���ĵ�21A
	private String souVoucherNo;
	
	//ԭҵ���ļ��� 80g O ���ԭҵ�����и����ļ���ҵ��OFNΪԭҵ���FLN or CLN��Ӧ�����ļ�����
	private String souBusFileName;
	
	//����ǩ�� 256x O ��Ϊ201 211 301 311 241341�ķ��ر���ʱ���ֶ�Ϊ����
	private String signNumber;

	/**
	 * �޲ι��췽��
	 *
	 */
	public CommReport(){
		
	}
	
	/**
	 * ���в������췽��
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
	 * @param sourceStr �õ��ķ��ر���Դ��
	 * @return ������ı�����
	 * @throws ReportParseException ͨ�÷��ر��Ľ����쳣��
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
	 * @param crr ���Ķ������
	 * @return ���ر���
	 * @throws StringTransformException �ַ���ת���쳣
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
