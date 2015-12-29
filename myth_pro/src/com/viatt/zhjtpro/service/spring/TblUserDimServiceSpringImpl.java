/**
 * 
 */
package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.CryptionData;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblUserDimDAO;
import com.viatt.zhjtpro.service.ITblUserDimService;

/**
 * @author wuyingbiao
 * 
 */
public class TblUserDimServiceSpringImpl implements ITblUserDimService {

	private ITblUserDimDAO tblUserDimDAO;


	public ITblUserDimDAO getTblUserDimDAO() {
		return tblUserDimDAO;
	}

	public void setTblUserDimDAO(ITblUserDimDAO tblUserDimDAO) {
		this.tblUserDimDAO = tblUserDimDAO;
	}


	public Page findTblUserDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblUserDimDAO.findTblUserDim(start, num, whereClause);
		return page;
	}


	public TblUserDimBo save(TblUserDimBo bo) throws Exception {
		return tblUserDimDAO.save(bo);
	}

	public List<TblUserDimBo> findTblUserDimByWhere(String whereClause) throws Exception {
		return tblUserDimDAO.findTblUserDimByWhere(whereClause);
	}

	public TblUserDimBo findTblUserDimById(String TblUserId) {
		return tblUserDimDAO.getById(TblUserId);
	}

	public void removeUserDim(String TblUserId) {
		tblUserDimDAO.deleteTblUserDim(TblUserId);
	}
	
	/**
	 * �û���¼�ɹ����
	 */
	public boolean login(String loginName, String loginPwd) {
		try {
			loginPwd = (new CryptionData()).EncryptionStringData(loginPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		TblUserDimBo bo = tblUserDimDAO.getUserByLogName(loginName);

		if (bo == null)
			return false;
		if (loginPwd.equals(bo.getLogPwd()))
			return true;
		else
			return false;
	}
	
	public boolean findPassword(String emailAddress, String telNum) {
		return tblUserDimDAO.findPassword(emailAddress, telNum);
	}
	
	public TblUserDimBo getLoginUserInfo(String loginName) {
		TblUserDimBo user = tblUserDimDAO.getUserByLogName(loginName);
		return user;
	}

	@Override
	public void updatePassword(String logName, TblUserDimBo bo) throws Exception {
		tblUserDimDAO.updatePassword(logName, bo);
	}
}
