/**
 * 
 */
package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblPropertyDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblPropertyDimDAO;
import com.viatt.zhjtpro.service.ITblPropertyDimService;

/**
 * @author wuyingbiao
 * 
 */
public class TblPropertyDimServiceSpringImpl implements ITblPropertyDimService
{

	private ITblPropertyDimDAO tblPropertyDimDAO;


	public ITblPropertyDimDAO getTblPropertyDimDAO() {
		return tblPropertyDimDAO;
	}

	public void setTblPropertyDimDAO(ITblPropertyDimDAO tblPropertyDimDAO) {
		this.tblPropertyDimDAO = tblPropertyDimDAO;
	}


	public Page findTblPropertyDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblPropertyDimDAO.findTblPropertyDim(start, num, whereClause);
		return page;
	}


	public TblPropertyDimBo save(TblPropertyDimBo bo) throws Exception {
		return tblPropertyDimDAO.save(bo);
	}

	public List<TblPropertyDimBo> findTblPropertyDimByWhere(String whereClause) throws Exception {
		return tblPropertyDimDAO.findTblPropertyDimByWhere(whereClause);
	}

	public TblPropertyDimBo findTblPropertyDimById(String TblUserId) {
		return tblPropertyDimDAO.getById(TblUserId);
	}

	public void removePropertyDim(String TblUserId) {
		tblPropertyDimDAO.deleteTblPropertyDim(TblUserId);
	}

}
