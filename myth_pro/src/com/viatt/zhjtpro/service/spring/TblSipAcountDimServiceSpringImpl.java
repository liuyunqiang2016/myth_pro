/**
 * 
 */
package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblSipAcountDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblSipAcountDimDAO;
import com.viatt.zhjtpro.service.ITblSipAcountDimService;

public class TblSipAcountDimServiceSpringImpl implements ITblSipAcountDimService {

	private ITblSipAcountDimDAO tblSipAcountDimDAO;


	public ITblSipAcountDimDAO getTblUserDimDAO() {
		return tblSipAcountDimDAO;
	}

	public void setTblSipAcountDimDAO(ITblSipAcountDimDAO tblSipAcountDimDAO) {
		this.tblSipAcountDimDAO = tblSipAcountDimDAO;
	}


	public Page findTblSipAcountDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblSipAcountDimDAO.findTblSipAcountDim(start, num, whereClause);
		return page;
	}


	public TblSipAcountDimBo save(TblSipAcountDimBo bo) throws Exception {
		return tblSipAcountDimDAO.save(bo);
	}

	public List<TblSipAcountDimBo> findTblSipAcountDimByWhere(String whereClause) throws Exception {
		return tblSipAcountDimDAO.findTblSipAcountDimByWhere(whereClause);
	}

	public TblSipAcountDimBo findTblSipAcountDimById(String acountId) {
		return tblSipAcountDimDAO.getById(acountId);
	}

	public void removeSipAcountDim(String acountId) {
		tblSipAcountDimDAO.deleteTblSipAcountDim(acountId);
	}
	
	public void updateSipAcountBo(String username, TblSipAcountDimBo bo) throws Exception {
		tblSipAcountDimDAO.updateSipAcountBo(username, bo);
	}

	public TblSipAcountDimBo getLoginUserInfo(String username) {
		return tblSipAcountDimDAO.getSipAcountByLogName(username);
	}
}
