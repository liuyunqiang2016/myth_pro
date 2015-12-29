package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblFingDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblFingDimDAO;

public class TblFingDimHibernateDAO extends HibernateDaoSupport implements
		ITblFingDimDAO {

	public TblFingDimBo getById(String strId) {
		return (TblFingDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblFingDimBo",
				strId);
	}

	public Page findTblFingDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblFingDimBo ");
			buf.append(whereClause);
			buf.append("order by usr_no");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblFingDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblFingDimBo save(TblFingDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblFingDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblFingDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause );
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void deleteTblFingDim(String usrNo) {
		
		this.getHibernateTemplate().delete(getById(usrNo));
//		this.getSession().createQuery("delete from TblFingDimBo where Fing_code = ?")
//				.setString(0, TblFingId).executeUpdate();
	}


	public TblFingDimBo getTblFing(TblFingDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblFingDimBo where fing_usr = ?", new Object[]{bo.getFingUsr()});
		if(list.size() != 0)
			return (TblFingDimBo) list.get(0);
		else 
			return null;
	}

	/*根据设备号查询得到俱相关的指纹信息
	 * (non-Javadoc)
	 * @see com.viatt.zhjtpro.dao.ITblFingDimDAO#findTblFingDimByEquCode(java.lang.String)
	 */
	public List<TblFingDimBo> findTblFingDimByEquCode(String equCode) {
		List<TblFingDimBo> fingDimBoList = new ArrayList<TblFingDimBo>() ;
		
		String sql = "SELECT * FROM tbl_fing_dim dim  INNER JOIN  `tbl_fing_op` op on op.card_no=dim.usr_no WHERE op.equ_code='"+ equCode +"'  AND dim.fing_state='01'" ;
		SQLQuery  query = this.getSession().createSQLQuery(sql) ;
		List<Object[]> list = query.list() ;
		
		for (int i = 0; i < list.size(); i++) {
			TblFingDimBo bo = new TblFingDimBo() ;
			Object[] obj = list.get(i);
			bo.setUsrNo(obj[0]+"") ;
			bo.setFingUsr(obj[1]+"") ;
			bo.setRoomCode(obj[2]+"");
			bo.setFingImg1(obj[3]+"");
			bo.setFingImg2(obj[4]+"");
			bo.setFingState(obj[5]+"") ;
			
			fingDimBoList.add(bo) ;
		}
		
		return fingDimBoList;
	}

}	
