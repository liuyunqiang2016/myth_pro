package com.ccb.net.models;

import biz.common.IModel;

public class I1001Model implements IModel{
	private String msgId ;
	
	private String IpAdd;
	
	private String roomID ;
	
	/**
	 * @param equId the equId to set
	 */
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
 
	public String getIpAdd() {
		return IpAdd;
	}
	public void setIpAdd(String ipAdd) {
		IpAdd = ipAdd;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
}
