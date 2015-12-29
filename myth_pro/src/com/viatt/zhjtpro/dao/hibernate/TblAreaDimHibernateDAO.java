package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblAreaDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblAreaDimDAO;

public class TblAreaDimHibernateDAO extends HibernateDaoSupport implements
		ITblAreaDimDAO {

	public TblAreaDimBo getById(String areaCode ,String commCode) {
		List<TblAreaDimBo> list = this.getHibernateTemplate().find(
				"from TblAreaDimBo where area_code = ? and comm_Code = ?",
				new Object[] { areaCode, commCode });
		if (list.size() != 0)
			return (TblAreaDimBo) list.get(0);
		else
			return null;
	}
	
	public TblAreaDimBo getAreaById(String areaCode ,String commCode) {
		List list = this.getHibernateTemplate().find(
				"from TblAreaDimBo where area_code = ? and comm_Code = ?",
				new Object[] { areaCode, commCode });
		if (list.size() != 0)
			return (TblAreaDimBo) list.get(0);
		else
			return null;
	}

	public Page findTblAreaDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblAreaDimBo ");
			buf.append(whereClause);
			buf.append("order by Area_name");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblAreaDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("=========e:"+e.getMessage());
			return new Page();
		}
	}

	public TblAreaDimBo save(TblAreaDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblAreaDimBo> findTblAreaDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblAreaDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			buf.append(" order by Area_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblAreaDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblAreaDimBo>();
		}
	}

	public void deleteTblAreaDim(String areaCode ,String commCode) {
		
		this.getHibernateTemplate().delete(getAreaById(areaCode,commCode));
	}


	public TblAreaDimBo getTblArea(TblAreaDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblAreaDimBo where Area_Name = ?", new Object[]{bo.getAreaName()});
		if(list.size() != 0)
			return (TblAreaDimBo) list.get(0);
		else 
			return null;
	}

}	
