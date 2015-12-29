package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblMenuDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblMenuDimDAO;
import com.viatt.zhjtpro.service.ITblMenuDimService;

public class TblMenuDimServiceSpringImpl implements ITblMenuDimService {
	private ITblMenuDimDAO tblMenuDimDAO;


	public ITblMenuDimDAO getTblMenuDimDAO() {
		return tblMenuDimDAO;
	}

	public void setTblMenuDimDAO(ITblMenuDimDAO tblMenuDimDAO) {
		this.tblMenuDimDAO = tblMenuDimDAO;
	}


	public Page findTblMenuDim(int start, int num, String whereClause)
			throws Exception {
		Page page = tblMenuDimDAO.findTblMenuDim(start, num, whereClause);
		return page;
	}


	public TblMenuDimBo save(TblMenuDimBo bo) throws Exception {
		return tblMenuDimDAO.save(bo);
	}

	public List<TblMenuDimBo> findTblMenuDimByWhere(String whereClause) throws Exception {
		return tblMenuDimDAO.findTblMenuDimByWhere(whereClause);
	}

	public TblMenuDimBo findTblMenuDimById(String TblMenuId) {
		return tblMenuDimDAO.getById(TblMenuId);
	}

	public void removeMenuDim(String TblMenuId) {
		tblMenuDimDAO.deleteTblMenuDim(TblMenuId);
	}

	public String getMenuString(String url) throws Exception {
		String strutl="";
		List<TblMenuDimBo> list = tblMenuDimDAO.findTblMenuDimByWhere(" where menu_url like'%"+ url+"%'");
		if(list!=null&&list.size()>0){
			TblMenuDimBo bo = (TblMenuDimBo)list.get(0);
			strutl="&menuPare="+bo.getMenuPar()+"&menuChild="+bo.getMenuCode();
		}
		return strutl;
	}

}
