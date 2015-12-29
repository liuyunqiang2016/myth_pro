package com.viatt.zhjtpro.service.spring;

import java.util.List;

import com.viatt.zhjtpro.bo.TblFingOpBo;
import com.viatt.zhjtpro.dao.ITblFingOpDAO;
import com.viatt.zhjtpro.service.ITblFingOpService;

public class TblFingOpServiceSpringImpl implements ITblFingOpService {
	private ITblFingOpDAO tblFingOpDAO;


	public ITblFingOpDAO getTblFingOpDAO() {
		return tblFingOpDAO;
	}

	public void setTblFingOpDAO(ITblFingOpDAO tblFingOpDAO) {
		this.tblFingOpDAO = tblFingOpDAO;
	}


	public TblFingOpBo save(TblFingOpBo bo) throws Exception {
		return tblFingOpDAO.save(bo);
	}

	public List findTblFingOpByWhere(String whereClause) throws Exception {
		return tblFingOpDAO.findTblFingOpByWhere(whereClause);
	}

	public void removeDim(String cardNo ,String opType) throws Exception{
		List list = tblFingOpDAO.findTblFingOpByWhere(" where card_no='"+cardNo+"' and op_type="+opType);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				TblFingOpBo bo = (TblFingOpBo)list.get(i);
				tblFingOpDAO.deleteBo(bo);
			}
		}
		
	}

}
