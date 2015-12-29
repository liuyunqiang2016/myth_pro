package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblInfoStatusDimBo;
import com.viatt.zhjtpro.bo.TblInnerEquOpBo;
import com.viatt.zhjtpro.bo.TblInnerStatusDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblInnerStatusDimDAO;

public class TblInnerStatusDimHibernateDAO extends HibernateDaoSupport
		implements ITblInnerStatusDimDAO {

	public TblInnerStatusDimBo getById(String innerid, String outerid, String optype) {
		List list = this
				.getHibernateTemplate()
				.find(
						"from TblInnerStatusDimBo where innerid = ? and outerid = ? and optype = ?",
						new Object[] { innerid, outerid, optype });
		if (list.size() != 0)
			return (TblInnerStatusDimBo) list.get(0);
		else
			return null;
	}

	public TblInnerStatusDimBo save(TblInnerStatusDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List findTblInnerStatusDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblInnerStatusDimBo ");
			if (whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			List list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public void delete(String innerid, String outerid, String optype) {
		this.getHibernateTemplate().delete(getById(innerid, outerid, optype));
	}

	public Page findTblInnerStatusDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblInnerStatusDimBo ");
			buf.append(whereClause);
			buf.append(" order by bak1 desc");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblInnerStatusDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}
	public void delete(TblInnerStatusDimBo bo) {
		this.getHibernateTemplate().delete(bo);
	}
	
	public void deleteList(String whereClause) throws Exception{
		StringBuffer buf = new StringBuffer("from TblInnerStatusDimBo ");
		if(whereClause != null || !"".equals(whereClause))
			buf.append(whereClause);
		Query query = getSession().createQuery(buf.toString());
		List list = query.list();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				TblInnerStatusDimBo info = (TblInnerStatusDimBo)list.get(i);
				delete(info);
			}
		}
	}

}
