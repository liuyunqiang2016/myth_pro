package com.viatt.zhjtpro.bo;


public class TblCardDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4577744445495131884L;

	public static final String RESI_GRPID = "3402223969890";

	private String cardNo;
	private String roomCode;
	private String usrName;
	private String userNo;
	private String state;
	private String beginDate;
	private String endDate;
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
	 * @return the roomCode
	 */
	public String getRoomCode() {
		return roomCode;
	}
	/**
	 * @param roomCode the roomCode to set
	 */
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	/**
	 * @return the usrName
	 */
	public String getUsrName() {
		return usrName;
	}
	/**
	 * @param usrName the usrName to set
	 */
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}
	/**
	 * @param userNo the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the beginDate
	 */
	public String getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
