package com.viatt.zhjtpro.bo;

public class TblFingOpBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6728348299135176387L;
	private String cardNo;
	private String opType;
	private String equCode;
	private String modCode;
	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**     
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the opType
	 */
	public String getOpType() {
		return opType;
	}
	/**
	 * @param opType the opType to set
	 */
	public void setOpType(String opType) {
		this.opType = opType;
	}
	/**
	 * @return the equCode
	 */
	public String getEquCode() {
		return equCode;
	}
	/**
	 * @param equCode the equCode to set
	 */
	public void setEquCode(String equCode) {
		this.equCode = equCode;
	}
	/**
	 * @return the modCode
	 */
	public String getModCode() {
		return modCode;
	}
	/**
	 * @param modCode the modCode to set
	 */
	public void setModCode(String modCode) {
		this.modCode = modCode;
	}
}
