package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblBuildingDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblBuildingDimDAO;

public class TblBuildingDimHibernateDAO extends HibernateDaoSupport implements
		ITblBuildingDimDAO {

	public TblBuildingDimBo getById(String BuildingCode ,String areaCode,String commCode) {
		List<TblBuildingDimBo> list = this.getHibernateTemplate().find(
				"from TblBuildingDimBo where building_code = ? and area_Code = ? and comm_Code = ?",
				new Object[] { BuildingCode,areaCode, commCode });
		if (list.size() != 0)
			return (TblBuildingDimBo) list.get(0);
		else
			return null;
	}
	
	public TblBuildingDimBo getBuildById(String BuildingCode ,String areaCode,String commCode) {
		List list = this.getHibernateTemplate().find(
				"from TblBuildingDimBo where building_code = ? and area_Code = ? and comm_Code = ?",
				new Object[] { BuildingCode,areaCode, commCode });
		if (list.size() != 0)
			return (TblBuildingDimBo) list.get(0);
		else
			return null;
	}

	public Page findTblBuildingDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from VBuildingDimBo ");
			buf.append(whereClause);
			buf.append("order by Building_name");
			  
			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from VBuildingDimBo  "
							+ (whereClause == null || "".equals(whereClause.trim()) ? "" : whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("=========e:"+e.getMessage());
			return new Page();
		}
	}

	public TblBuildingDimBo save(TblBuildingDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblBuildingDimBo> findTblBuildingDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblBuildingDimBo ");
			if(whereClause != null || !"".equals(whereClause))
			{
				buf.append(whereClause);
			}
			buf.append(" order by Building_Name");
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblBuildingDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblBuildingDimBo>();
		}
	}

	public void deleteTblBuildingDim(String BuildingCode ,String areaCode,String commCode) {
		
		this.getHibernateTemplate().delete(getBuildById(BuildingCode,areaCode,commCode));
//		this.getSession().createQuery("delete from TblBuildingDimBo where Building_code = ?")
//				.setString(0, TblBuildingId).executeUpdate();
	}


	public TblBuildingDimBo getTblBuilding(TblBuildingDimBo bo) {
		List list = this.getHibernateTemplate().find("from TblBuildingDimBo where Building_Name = ?", new Object[]{bo.getBuildingName()});
		if(list.size() != 0)
			return (TblBuildingDimBo) list.get(0);
		else 
			return null;
	}

}	
