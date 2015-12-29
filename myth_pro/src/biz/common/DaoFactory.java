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
 * <p>Title:SQL工厂类 </p>
 * <p>Description: 执行SQL</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author  
 * @version 1.0
 */
public class DaoFactory extends HibernateDaoSupport implements IDao{
	/**
	 * 增加记录
	 * 
	 * @param Object
	 *            entity,需要保存的BO
	 */
	public Object save(Object entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	/**
	 * 增加或修改记录
	 * 
	 * @param Object
	 *            entity,需要保存的BO
	 */
	public Object saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * 删除记录
	 * 
	 * @param Object
	 *            entity,需要删除的BO
	 */
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据id删除记录
	 * 
	 * @param Class
	 *            entityClass,BO的类型
	 * @param Serializable
	 *            id,BO的id值
	 */
	public void deleteById(Class entityClass, Serializable id) {
		getHibernateTemplate().delete(this.findById(entityClass, id));
	}

	/**
	 * 删除所有记录
	 * 
	 * @param Class
	 *            entityClass,BO的类型
	 */
	public void deleteAll(Collection entities) {
		getHibernateTemplate().deleteAll(entities);
		;
	}

	/**
	 * 修改记录
	 * 
	 * @param Object,要修改的BO
	 */
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 通过id获得记录
	 * 
	 * @param Class
	 *            entityClass,对象类型
	 * @param Serializable
	 *            id,BO的id值
	 */
	public Object findById(Class entityClass, Serializable id) {

		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 通过任意一个BO属性获得记录
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String
	 *            attributename,BO属性的名字
	 * @param Object
	 *            attributevalue,BO的属性的值
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
	 * 通过任意一个String型BO属性获得记录
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String
	 *            attributename,BO属性的名字
	 * @param Object
	 *            attributevalue,BO的属性的值
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
	 * 通过任意多个BO属性获得记录
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String[]
	 *            attributename,多个BO属性的名字
	 * @param Object[]
	 *            attributevalue,多个BO的属性的值
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
	 * 通过任意多个BO属性获得记录
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String[]
	 *            attributename,多个BO属性的名字
	 * @param Object[]
	 *            attributevalue,多个BO的属性的值
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
	 * 获得所有记录
	 * 
	 * @param String
	 *            boname,BO的名字
	 */
	public List findAll(String boname) {
		String hql = "from " + boname;
		BGMLogger.LogInfo("查询所有记录sql:"+hql);
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 获得记录总数
	 * 
	 * @param String
	 *            boname,BO的名字
	 */
	public Integer getBOCount(String boname) {
		String hql = "select count(bo) from " + boname + " as bo";
		List list = getHibernateTemplate().find(hql);
		Integer ret = (Integer) list.get(0);
		return ret;
	}

	/**
	 * 根据属性条件获得记录总数
	 * 
	 * @param String
	 *            boname,BO的名字
	 */
	public Integer getBOCount(String boname, String attributename,
			Object attributevalue) {
		String hql = "select count(bo) from " + boname + " as bo where bo."
				+ attributename + "=" + attributevalue;
		BGMLogger.LogDebug("getBOCount方法的hql:" + hql);
		List list = getHibernateTemplate().find(hql);
		// Integer ret = (Integer)list.get(0);
		Integer ret = Integer.valueOf(list.get(0).toString());
		return ret;
	}

	/**
	 * 获得排序hql字符串
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String
	 *            attributename,需要排序的BO属性名字
	 * @param int
	 *            order,升序为0，降序为1
	 */
	public String getOrderString(String attributename, int order) {
		if (order == 0) {
			return " order by bo." + attributename;
		} else {
			return " order by bo." + attributename + " desc";
		}
	}

	/**
	 * 获得排序hql字符串
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String
	 *            attributename,需要排序的BO属性名字
	 * @param int
	 *            order,升序为0，降序为1
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
	 * 获得分组hql字符串
	 * 
	 * @param String
	 *            boname,BO的名字
	 * @param String
	 *            attributename,需要排序的BO属性名字
	 */
	/*
	 * public String getGroupString(String attributename){ return "group by
	 * bo."+attributename; }
	 */

	/**
	 * 通过引用查询得到查询结果
	 * 
	 * @param String
	 *            queryName，查询名字
	 */
	public List findByNamedQuery(String queryName) {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * 通过引用查询得到查询结果
	 * 
	 * @param String
	 *            queryName,查询名字
	 * @param String
	 *            paramName,参数的名字
	 * @param String
	 *            value,参数的值
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramName, value);
	}

	/**
	 * 通过引用查询得到查询结果
	 * 
	 * @param String
	 *            queryName，查询名字
	 * @param String[]
	 *            paramName,参数名字的数组
	 * @param String[]
	 *            value,参数值的数组
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName,
				paramNames, values);
	}

	/**
	 * 执行批量删除操作
	 * 
	 * @param String
	 *            boname，BO的名字
	 * @param String
	 *            attributename,删除条件：BO属性名字
	 * @param Object
	 *            attributevalue,删除条件：BO属性值
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
	 * 执行批量修改操作
	 * 
	 * @param String
	 *            boname，BO的名字
	 * @param String
	 *            attributename,需要修改的BO属性名字
	 * @param Object
	 *            attributevalue,修改后的BO属性值
	 * @param String
	 *            condition,修改条件
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
	 * 获得延迟加载数据
	 * 
	 * @param Object
	 *            object，需要加载的对象
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
