package biz.common.commbean;

import java.util.Map;

import biz.common.IModel;

/**
 * ���ش�����
 * @author ���޾�
 * 20100322
 */
public class PubReturnCode implements IModel{

	//���ش�����
	private String errCode;
	
	//���ش�����Ϣ
	private String errMsg;
	
	//��ˮ��
	private String flowNo;
	
//	�ܼ�¼��
	private Integer totalCount;
	
	//��ҳ��
	private Integer totalPage;
	
	
	//�ܼ�¼����
	private String total;
	
	//��ǰҳ
	private String currPage;
	
	//ҳ����ʾ��¼��
	private String pageSize;
	
	private Map map;
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}


	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	
	
}
