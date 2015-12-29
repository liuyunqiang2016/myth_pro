package com.viatt.zhjtpro.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.viatt.zhjtpro.bo.TblRoomDimBo;
import com.viatt.zhjtpro.bo.VRoomDimBo;
import com.viatt.zhjtpro.common.Page;
import com.viatt.zhjtpro.dao.ITblRoomDimDAO;

public class TblRoomDimHibernateDAO extends HibernateDaoSupport implements
		ITblRoomDimDAO {

	public Page findTblRoomDim(int start, int num, String whereClause)
			throws Exception {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("from VRoomDimBo ");
			buf.append(whereClause);

			Query query = getSession().createQuery(buf.toString());
			query.setFirstResult(start);
			query.setMaxResults(num);
			List list = query.list();

			Page page = new Page(start, num, list);
			page.setTotalNumber(((Integer) getHibernateTemplate().iterate(
					"select count(*) from VRoomDimBo  "
							+ (whereClause == null
									|| "".equals(whereClause.trim()) ? ""
									: whereClause.trim())).next()).intValue());
			return page;
		} catch (Exception e) {
			System.out.println("=========e:" + e.getMessage());
			return new Page();
		}
	}

	public TblRoomDimBo save(TblRoomDimBo bo) {
		getHibernateTemplate().saveOrUpdate(bo);
		return bo;
	}

	public List<TblRoomDimBo> findVRoomDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblRoomDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblRoomDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblRoomDimBo>();
		}
	}
	
	public List<TblRoomDimBo> findTblRoomDimByWhere(String whereClause) throws Exception {
		try {
			StringBuffer buf = new StringBuffer("from TblRoomDimBo ");
			if(whereClause != null || !"".equals(whereClause))
				buf.append(whereClause);
			Query query = getSession().createQuery(buf.toString());
			
			@SuppressWarnings("unchecked")
			List<TblRoomDimBo> list = query.list();
			return list;
		} catch (Exception e) {
			return new ArrayList<TblRoomDimBo>();
		}
	}

	public TblRoomDimBo getTblRoom(TblRoomDimBo bo) {
		List list = this.getHibernateTemplate().find(
				"from TblRoomDimBo where Room_usr = ?",
				new Object[] { bo.getRoomUsr() });
		if (list.size() != 0)
			return (TblRoomDimBo) list.get(0);
		else
			return null;
	}

	public void deleteTblRoomDim(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode) {
		this.getHibernateTemplate().delete(getRoomById(roomCode, areaCode, commCode, unitCode,
				buildingCode));

	}

	public TblRoomDimBo getById(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode) {
		List<TblRoomDimBo> list = this
				.getHibernateTemplate()
				.find(
						"from TblRoomDimBo where building_code = ? and area_Code = ? and comm_Code = ? and room_code = ? and unit_code = ? ",
						new Object[] { buildingCode, areaCode, commCode,
								roomCode, unitCode });
		if (list.size() != 0)
			return (TblRoomDimBo) list.get(0);
		else
			return null;
	}
	
	public String getJsonRoomInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode){
		List list = this
				.getHibernateTemplate()
				.find(
						"from TblRoomDimBo where area_Code=? and comm_Code=? and  unit_code=? and building_code=? ",
						new Object[] {areaCode, commCode,
								unitCode, buildingCode });
		if (list.size() != 0) {
			StringBuilder sb = new StringBuilder("{\"roominfo\":\"");
			VRoomDimBo roomBean = (VRoomDimBo) list.get(0); 
			//roomBean.getCommName() ���ҪС�����֣�������䡣 ������û����ʾС������
			String jsonRoom = roomBean.getCommName() + roomBean.getAreaName()
					+ roomBean.getBuildingName() + "¥��" + roomBean.getUnitName()
					+ roomCode +"��";
			sb.append(jsonRoom).append("\"}");
			return sb.toString();
		}
		return null;
	}
	
	/*
	 * �õ��ſڻ�����Ϣ
	 */
	public String getJsonOutdoorInfo(String roomCode, String areaCode, String commCode, String unitCode, String buildingCode){
		List list = this
		.getHibernateTemplate()
		.find(
				"from TblRoomDimBo where area_Code=? and comm_Code=? and  unit_code=? and building_code=? ",
				new Object[] {areaCode, commCode,
						unitCode, buildingCode });
		if (list.size() != 0) {
			StringBuilder sb = new StringBuilder("{\"roominfo\":\"");
			VRoomDimBo roomBean = (VRoomDimBo) list.get(0); 
			//roomBean.getCommName() ���ҪС�����֣�������䡣 ������û����ʾС������
			String jsonRoom = roomBean.getAreaName()
			+ roomBean.getBuildingName() + "¥" + roomBean.getUnitName()
			+ roomCode +"��";
			sb.append(jsonRoom).append("\"}");
			return sb.toString();
		}
		return null;
	}

	public TblRoomDimBo getRoomById(String roomCode, String areaCode,
			String commCode, String unitCode, String buildingCode) {
		List list = this
				.getHibernateTemplate()
				.find(
						"from TblRoomDimBo where building_code = ? and area_Code = ? and comm_Code = ? and room_code = ? and unit_code = ? ",
						new Object[] { buildingCode, areaCode, commCode,
								roomCode, unitCode });
		if (list.size() != 0)
			return (TblRoomDimBo) list.get(0);
		else
			return null;
	}
}
