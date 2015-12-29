package com.ccb.net.models;

import biz.common.IModel;

public class OutModel implements IModel {
	private String msgId;
	private String reCode;
	private String reMsg;
	/**
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the reCode
	 */
	public String getReCode() {
		return reCode;
	}
	/**
	 * @param reCode the reCode to set
	 */
	public void setReCode(String reCode) {
		this.reCode = reCode;
	}
	/**
	 * @return the reMsg
	 */
	public String getReMsg() {
		return reMsg;
	}
	/**
	 * @param reMsg the reMsg to set
	 */
	public void setReMsg(String reMsg) {
		this.reMsg = reMsg;
	}

}
