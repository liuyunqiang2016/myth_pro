package com.viatt.zhjtpro.bo;

/*
 * 指纹发送状态
 */
public class TblFingStatusDimBo extends BaseBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int statusId ;
	private String equCode;
	private String ipAdd ;
	private String usrNo ;
	private String fingImg1;
	private String fingImg2;
	private String opType; // 操作类型 增加，修改，类型
	private String sendZt;

	
	public String getIpAdd() {
		return ipAdd;
	}

	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	
	public String getUsrNo() {
		return usrNo;
	}

	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}

	/**
	 * @return the equCode
	 */
	public String getEquCode() {
		return equCode;
	}

	/**
	 * @param equCode
	 *            the equCode to set
	 */
	public void setEquCode(String equCode) {
		this.equCode = equCode;
	}

	public String getFingImg1() {
		return fingImg1;
	}

	public void setFingImg1(String fingImg1) {
		this.fingImg1 = fingImg1;
	}

	public String getFingImg2() {
		return fingImg2;
	}

	public void setFingImg2(String fingImg2) {
		this.fingImg2 = fingImg2;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getSendZt() {
		return sendZt;
	}

	public void setSendZt(String sendZt) {
		this.sendZt = sendZt;
	}

}
