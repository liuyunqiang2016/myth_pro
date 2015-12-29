package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblOwnerDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblOwnerDimDAO;

public class TblOwnerDimHibernateDAO extends HibernateDaoSupport implements
		ITblOwnerDimDAO {

	public TblOwnerDimBo getById(String strId) {
		return (TblOwnerDimBo) getHibernateTemplate().get("com.viatt.zhjtpro.bo.TblOwnerDimBo",
				strId);
	}

	public Page findTblOwnerDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from TblOwnerDimBo ");
			buf.append(whereClause);
			buf.append("order by Owner_name");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from TblOwnerDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("======================e:"+e.getMessage());
			return new Page();
		}
	}

	public TblOwnerDimBo update(TblOwnerDimBo bo) {
		getHibernateTemplate().update(bo);
		return bo;
	}
	
	public TblOwnerDimBo save(TblOwnerDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblOwnerDimBo> findTblOwnerDimByWhere(String key, String value) throws Exception
	{
		try
		{
			StringBuffer buf = new StringBuffer("from TblOwnerDimBo ");
			if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value))
			{
				buf.append(" where " + key + " = '" + value + "'");
			}
			buf.append(" order by Owner_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblOwnerDimBo> list = query.list();
			return list;
		}
		catch (Exception e)
		{
			return new ArrayList<TblOwnerDimBo>();
		}
	}

	public void deleteTblOwnerDim(String ownerCode) {
		
		this.getHibernateTemplate().delete(getById(ownerCode));
//		this.getSession().createQuery("delete from TblOwnerDimBo where Owner_code = ?")
//				.setString(0, TblOwnerId).executeUpdate();
	}


	public TblOwnerDimBo getTblOwner(TblOwnerDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblOwnerDimBo where Owner_Name = ?", new Object[]{bo.getOwnerName()});
		if(list.size() != 0)
			return (TblOwnerDimBo) list.get(0);
		else 
			return null;
	}

	public String getMaxId() {
		String id = "1";
		try {
			StringBuffer buf = new StringBuffer(
					"select max(owner_code) from tbl_owner_dim ");
			Query query = getSession().createSQLQuery(buf.toString());
			List list = query.list();
			if(list.size()!=0){
				String obj = (String) list.get(0);
				id = Integer.parseInt(obj)+1+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}	
