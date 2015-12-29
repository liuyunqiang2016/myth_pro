package com.viatt.zhjtpro.bo;

public class TblInnerStatusDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7773082940025467440L;
	private String innerid;
	private String outerid;
	private String optype;
	private String pertype;
	private String sendzt;
	private String remark;
	public String getPertype() {
		return pertype;
	}
	public void setPertype(String pertype) {
		this.pertype = pertype;
	}
	public String getSendzt() {
		return sendzt;
	}
	public void setSendzt(String sendzt) {
		this.sendzt = sendzt;
	}
	private String bak1;
	private String bak2;
	public String getInnerid() {
		return innerid;
	}
	public void setInnerid(String innerid) {
		this.innerid = innerid;
	}
	public String getOuterid() {
		return outerid;
	}
	public void setOuterid(String outerid) {
		this.outerid = outerid;
	}
	public String getOptype() {
		return optype;
	}
	public void setOptype(String optype) {
		this.optype = optype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
}
