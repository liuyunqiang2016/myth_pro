/**
 * 
 */
package com.viatt.zhjtpro.dao;

import java.util.List;

import com.viatt.zhjtpro.bo.TblUserDimBo;
import com.viatt.zhjtpro.common.Page;

public interface ITblUserDimDAO {

	public abstract TblUserDimBo getById(String strId);

	public abstract Page findTblUserDim(int start, int num, String whereClause)
			throws Exception;
	
	public abstract TblUserDimBo save(TblUserDimBo bo);
	
	public abstract void updatePassword(String logName, TblUserDimBo bo) throws Exception;
	
	public abstract List<TblUserDimBo> findTblUserDimByWhere(String whereClause) throws Exception;

	public abstract void deleteTblUserDim(String tblUserId);
	
	public abstract TblUserDimBo getUserByLogName(String logName);
	
	public abstract boolean findPassword(String emailAddress, String telNum);

}
