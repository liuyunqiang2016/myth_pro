package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblUnitDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblUnitDimDAO;

public class TblUnitDimHibernateDAO extends HibernateDaoSupport implements
		ITblUnitDimDAO {

	public TblUnitDimBo getById(String buildingCode, String areaCode,
			String commCode, String unitCode) {
		List<TblUnitDimBo> list = this
				.getHibernateTemplate()
				.find(
						"from TblUnitDimBo where building_code = ? and area_Code = ? and comm_Code = ? and Unit_code = ?",
						new Object[] { buildingCode, areaCode, commCode,
								unitCode });
		if (list.size() != 0)
			return (TblUnitDimBo) list.get(0);
		else
			return null;
	}

	public TblUnitDimBo getUnitById(String buildingCode, String areaCode,
			String commCode, String unitCode) {
		List list = this
				.getHibernateTemplate()
				.find(
						"from TblUnitDimBo where building_code = ? and area_Code = ? and comm_Code = ? and Unit_code = ?",
						new Object[] { buildingCode, areaCode, commCode,
								unitCode });
		if (list.size() != 0)
			return (TblUnitDimBo) list.get(0);
		else
			return null;
	}

	public Page findTblUnitDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from VUnitDimBo ");
			buf.append(whereClause);
			buf.append("order by Unit_name");

			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from VUnitDimBo  "
							+ (whereClause == null
									|| "".equals(whereClause.trim()) ? ""
									: whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("=========e:" + e.getMessage());
			return new Page();
		}
	}

	public TblUnitDimBo save(TblUnitDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblUnitDimBo> findTblUnitDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblUnitDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblUnitDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblUnitDimBo>();
		}
	}

	public void deleteTblUnitDim(String buildingCode, String areaCode,
			String commCode, String unitCode) {

		this.getHibernateTemplate().delete(
				getUnitById(buildingCode, areaCode, commCode, unitCode));
	}

	public TblUnitDimBo getTblUnit(TblUnitDimBo bo) {
		List list = this.getHibernateTemplate().find(
				"from TblUnitDimBo where Unit_Name = ?",
				new Object[] { bo.getUnitName() });
		if (list.size() != 0)
			return (TblUnitDimBo) list.get(0);
		else
			return null;
	}

}
