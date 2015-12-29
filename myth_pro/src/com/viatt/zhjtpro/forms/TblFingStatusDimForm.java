package com.viatt.zhjtpro.forms;


public class TblFingStatusDimForm extends BaseForm {
	private int id ;
	private String equCode;
	private String fingImg1;
	private String fingImg2;
	private String opType;
	private String sendZt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEquCode() {
		return equCode;
	}
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