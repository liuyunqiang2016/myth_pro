package biz.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 *
 * <p>Title:SQL������ </p>
 * <p>Description: ִ��SQL</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author  
 * @version 1.0
 */
public class DaoFactory extends HibernateDaoSupport implements IDao{
	/**
	 * ���Ӽ�¼
	 * 
	 * @param Object
	 *            entity,��Ҫ�����BO
	 */
	public Object save(Object entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	/**
	 * ���ӻ��޸ļ�¼
	 * 
	 * @param Object
	 *            entity,��Ҫ�����BO
	 */
	public Object saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * ɾ����¼
	 * 
	 * @param Object
	 *            entity,��Ҫɾ����BO
	 */
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * ����idɾ����¼
	 * 
	 * @param Class
	 *            entityClass,BO������
	 * @param Serializable
	 *            id,BO��idֵ
	 */
	public void deleteById(Class entityClass, Serializable id) {
		getHibernateTemplate().delete(this.findById(entityClass, id));
	}

	/**
	 * ɾ�����м�¼
	 * 
	 * @param Class
	 *            entityClass,BO������
	 */
	public void deleteAll(Collection entities) {
		getHibernateTemplate().deleteAll(entities);
		;
	}

	/**
	 * �޸ļ�¼
	 * 
	 * @param Object,Ҫ�޸ĵ�BO
	 */
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * ͨ��id��ü�¼
	 * 
	 * @param Class
	 *            entityClass,��������
	 * @param Serializable
	 *            id,BO��idֵ
	 */
	public Object findById(Class entityClass, Serializable id) {

		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * ͨ������һ��BO���Ի�ü�¼
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String
	 *            attributename,BO���Ե�����
	 * @param Object
	 *            attributevalue,BO�����Ե�ֵ
	 */
	public List findByAny(String boname, String attributename,
			Object attributevalue, String order) {
		if (order == null)
			order = "";
		String hql = "from " + boname + " as bo where bo." + attributename
				+ "= '" + attributevalue+"' " + order;
		BGMLogger.LogInfo(hql);
		return getHibernateTemplate().find(hql);
	}

	/**
	 * ͨ������һ��String��BO���Ի�ü�¼
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String
	 *            attributename,BO���Ե�����
	 * @param Object
	 *            attributevalue,BO�����Ե�ֵ
	 */
	public List findByAnyString(String boname, String attributename,
			String attributevalue, String order) {
		if (order == null)
			order = "";
		String hql = "from " + boname + " as bo where bo." + attributename
				+ "='" + attributevalue + "'" + order;
		BGMLogger.LogDebug(hql);
		return getHibernateTemplate().find(hql);
	}

	/**
	 * ͨ��������BO���Ի�ü�¼
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String[]
	 *            attributename,���BO���Ե�����
	 * @param Object[]
	 *            attributevalue,���BO�����Ե�ֵ
	 */
	public List findByAny(String boname, String[] attributename,
			Object[] attributevalue, String order) {
		if (order == null)
			order = "";
		String hql = "from " + boname + " as bo where ";
		if (attributename.length == attributevalue.length) {
			for (int i = 0; i < attributename.length; i++) {
				if (i == 0) {
					hql = hql + "bo." + attributename[i] + "='"
							+ attributevalue[i]+"' ";
				} else {
					hql = hql + " and bo." + attributename[i] + "='"
							+ attributevalue[i]+"' ";
				}
			}
			hql = hql + order;

			BGMLogger.LogInfo("Hql:"+hql);
			return getHibernateTemplate().find(hql);
		} else {
			return null;
		}

	}

	/**
	 * ͨ��������BO���Ի�ü�¼
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String[]
	 *            attributename,���BO���Ե�����
	 * @param Object[]
	 *            attributevalue,���BO�����Ե�ֵ
	 */
	public List findByAnyString(String boname, String[] attributename,
			String[] attributevalue, String order) {
		if (order == null)
			order = "";
		String hql = "from " + boname + " as bo where ";

		if (attributename.length == attributevalue.length) {
			for (int i = 0; i < attributename.length; i++) {
				if (i == 0) {
					hql = hql + "bo." + attributename[i] + "='"
							+ attributevalue[i] + "' ";
				} else {
					hql = hql + " and bo." + attributename[i] + "='"
							+ attributevalue[i] + "' ";
				}
			}
			hql = hql + order;

			BGMLogger.LogDebug(hql);

			return getHibernateTemplate().find(hql);
		} else {
			return null;
		}

	}

	/**
	 * ������м�¼
	 * 
	 * @param String
	 *            boname,BO������
	 */
	public List findAll(String boname) {
		String hql = "from " + boname;
		BGMLogger.LogInfo("��ѯ���м�¼sql:"+hql);
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * ��ü�¼����
	 * 
	 * @param String
	 *            boname,BO������
	 */
	public Integer getBOCount(String boname) {
		String hql = "select count(bo) from " + boname + " as bo";
		List list = getHibernateTemplate().find(hql);
		Integer ret = (Integer) list.get(0);
		return ret;
	}

	/**
	 * ��������������ü�¼����
	 * 
	 * @param String
	 *            boname,BO������
	 */
	public Integer getBOCount(String boname, String attributename,
			Object attributevalue) {
		String hql = "select count(bo) from " + boname + " as bo where bo."
				+ attributename + "=" + attributevalue;
		BGMLogger.LogDebug("getBOCount������hql:" + hql);
		List list = getHibernateTemplate().find(hql);
		// Integer ret = (Integer)list.get(0);
		Integer ret = Integer.valueOf(list.get(0).toString());
		return ret;
	}

	/**
	 * �������hql�ַ���
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String
	 *            attributename,��Ҫ�����BO��������
	 * @param int
	 *            order,����Ϊ0������Ϊ1
	 */
	public String getOrderString(String attributename, int order) {
		if (order == 0) {
			return " order by bo." + attributename;
		} else {
			return " order by bo." + attributename + " desc";
		}
	}

	/**
	 * �������hql�ַ���
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String
	 *            attributename,��Ҫ�����BO��������
	 * @param int
	 *            order,����Ϊ0������Ϊ1
	 */
	public String getOrderString(String[] attributenames, int[] orders) {

		String orderstring = " ";

		if (attributenames.length == orders.length) {
			for (int i = 0; i < attributenames.length; i++) {
				if (i == 0) {
					if (orders[i] == 0) {
						orderstring = orderstring + "order by bo."
								+ attributenames[i];
					} else {
						orderstring = orderstring + "order by bo."
								+ attributenames[i] + " desc";
					}
				} else {
					if (orders[i] == 0) {
						orderstring = orderstring + ",bo." + attributenames[i];
					} else {
						orderstring = orderstring + ",bo." + attributenames[i]
								+ " desc";
					}
				}
			}
			return orderstring;
		} else {
			return null;
		}

	}

	/**
	 * ��÷���hql�ַ���
	 * 
	 * @param String
	 *            boname,BO������
	 * @param String
	 *            attributename,��Ҫ�����BO��������
	 */
	/*
	 * public String getGroupString(String attributename){ return "group by
	 * bo."+attributename; }
	 */

	/**
	 * ͨ�����ò�ѯ�õ���ѯ���
	 * 
	 * @param String
	 *            queryName����ѯ����
	 */
	public List findByNamedQuery(String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * ͨ�����ò�ѯ�õ���ѯ���
	 * 
	 * @param String
	 *            queryName,��ѯ����
	 * @param String
	 *            paramName,����������
	 * @param String
	 *            value,������ֵ
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramName, value);
	}

	/**
	 * ͨ�����ò�ѯ�õ���ѯ���
	 * 
	 * @param String
	 *            queryName����ѯ����
	 * @param String[]
	 *            paramName,�������ֵ�����
	 * @param String[]
	 *            value,����ֵ������
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramNames, values);
	}

	/**
	 * ִ������ɾ������
	 * 
	 * @param String
	 *            boname��BO������
	 * @param String
	 *            attributename,ɾ��������BO��������
	 * @param Object
	 *            attributevalue,ɾ��������BO����ֵ
	 */
	public Integer bulkDelete(final String boname, final String attributename,
			final Object attributevalue) {
		Integer ret = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String deleteHQL = "delete " + boname + " where "
								+ attributename + "=" + attributevalue;
						System.out.println(deleteHQL);
						Query query = session.createQuery(deleteHQL);
						int r = query.executeUpdate();

						return new Integer(r);
					}
				});
		return ret;
	}

	/**
	 * ִ�������޸Ĳ���
	 * 
	 * @param String
	 *            boname��BO������
	 * @param String
	 *            attributename,��Ҫ�޸ĵ�BO��������
	 * @param Object
	 *            attributevalue,�޸ĺ��BO����ֵ
	 * @param String
	 *            condition,�޸�����
	 */
	public Integer bulkUpdate(final String boname,final String setattributename, final Object setattributevalue,final String condition) {
		Integer ret = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String updateHQL;
						if (condition == null) {
							updateHQL = "update " + boname + " set "
									+ setattributename + "="
									+ setattributevalue;
						} else {
							updateHQL = "update " + boname + " set "
									+ setattributename + "="
									+ setattributevalue + " where " + condition;
						}
						BGMLogger.LogDebug("updateHQL=" + updateHQL);
						Query query = session.createQuery(updateHQL);
						int r = query.executeUpdate();

						return new Integer(r);
					}
				});
		return ret;
	}

	/**
	 * ����ӳټ�������
	 * 
	 * @param Object
	 *            object����Ҫ���صĶ���
	 */
	public void getLazyData(Object object) {
		Hibernate.initialize(object);
	}

	@SuppressWarnings("deprecation")
	public List findPageByQuery(final String queryString,
			final Object[] parameters, final PageInfo pageInfo) {
		Query query = this.getSession().createSQLQuery(queryString);
		ScrollableResults sr = query.scroll();
		sr.last();
		int totalCount = sr.getRowNumber();
		int startIndex = (pageInfo.getPageIndex() - 1)
				* pageInfo.getPageSize();
		query.setMaxResults(pageInfo.getPageSize());
		query.setFirstResult(startIndex);
		int totalRec = totalCount + 1;
		pageInfo.setTotalRec(totalRec);
		int totalPage = (totalRec % pageInfo.getPageSize() == 0) ? (totalRec / pageInfo
				.getPageSize())
				: (totalRec / pageInfo.getPageSize()) + 1;
		int[] pageNumbers = new int[totalPage];
		for (int i = 0; i < totalPage; i++) {
			pageNumbers[i] = (i + 1);
		}
		pageInfo.setPageNumbers(pageNumbers);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setPageSize(pageInfo.getPageSize());
		pageInfo.setPageIndex(pageInfo.getPageIndex());
		pageInfo.setPrePage(pageInfo.getPageIndex() - 1);
		pageInfo.setNextPage(pageInfo.getPageIndex() + 1);
		return query.list();
		
	}


	@SuppressWarnings("deprecation")
	public List findPageByHqlQuery(final String queryString,
			final Object[] parameters, final PageInfo pageInfo) {
		Query query = getSession().createQuery(queryString);
		ScrollableResults sr = query.scroll();
		sr.last();
		int totalCount = sr.getRowNumber();
		int startIndex = (pageInfo.getPageIndex() - 1)
				* pageInfo.getPageSize();
		query.setMaxResults(pageInfo.getPageSize());
		query.setFirstResult(startIndex);
		int totalRec = totalCount + 1;
		BGMLogger.LogInfo("");
		pageInfo.setTotalRec(totalRec);
		int totalPage = (totalRec % pageInfo.getPageSize() == 0) ? (totalRec / pageInfo
				.getPageSize())
				: (totalRec / pageInfo.getPageSize()) + 1;
		int[] pageNumbers = new int[totalPage];
		for (int i = 0; i < totalPage; i++) {
			pageNumbers[i] = (i + 1);
		}
		pageInfo.setPageNumbers(pageNumbers);
		pageInfo.setTotalPage(totalPage);
		pageInfo.setPageSize(pageInfo.getPageSize());
		pageInfo.setPageIndex(pageInfo.getPageIndex());
		pageInfo.setPrePage(pageInfo.getPageIndex() - 1);
		pageInfo.setNextPage(pageInfo.getPageIndex() + 1);
		return query.list();	
	}
	
	private void setParameters(Object[] values, Query query)
			throws HibernateException {
		if (values == null) {
			return;
		}
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i + 1, values[i]);
		}
	}

	@SuppressWarnings("deprecation")
	public List findBySQLQuery(final String queryString,
			final Object[] parameters) {	
		BGMLogger.LogInfo(">>>>>>>>>>>>>>>>>>>>>>>>>>"+queryString);
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(queryString);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				return query.list();
			}
		}, true);
	}

	public boolean querySql(String sqlStr) {
		try {
			SessionFactory session = getHibernateTemplate().getSessionFactory();
			Session sesson2 = session.openSession();
			sesson2.beginTransaction();
			sesson2.createSQLQuery(sqlStr).executeUpdate();
			sesson2.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
