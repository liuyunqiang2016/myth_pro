package biz.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * <p>Title: </p>
 * <p>Description: DAO接口</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */
public interface IDao {
	/** 
	 * 增加记录 
	 * @param Object entity,需要保存的BO
	 */ 
	public Object save(Object entity);
	
	/** 
	 * 增加或修改记录 
	 * @param Object entity,需要保存的BO
	 */ 
	public Object saveOrUpdate(Object entity);
	
	/** 
	 * 删除记录 
	 * @param Object entity,需要删除的BO 
	 */ 
	public void delete(Object entity);
	
	/** 
	 * 根据id删除记录 
	 * @param Class entityClass,BO的类型 
	 * @param Serializable id,BO的id值
	 */ 
	public void deleteById(Class entityClass, Serializable id);
	
	/** 
	 * 删除所有记录 
	 * @param Collection entities,BO的集合 
	 */ 
	public void deleteAll(Collection entities);
	
	/** 
	 * 修改记录 
	 * @param Object,要修改的BO 
	 */ 
	public void update(Object entity);
	
	/** 
	 * 通过id获得记录 
	 * @param Serializable id,BO的id值 
	 */ 
	public Object findById(Class entityClass,Serializable id);
	
	/** 
	 * 通过任意一个BO属性获得记录 
	 * @param String boname,BO的名字
	 * @param String attributename,BO属性的名字
	 * @param Object attributevalue,BO的属性的值 
	 */ 
	public List findByAny(String boname,String attributename,Object attributevalue,String order);
	
	/** 
	 * 通过任意一个BO属性获得记录 
	 * @param String boname,BO的名字
	 * @param String attributename,BO属性的名字
	 * @param Object attributevalue,BO的属性的值 
	 */ 
	public List findByAnyString(String boname,String attributename,String attributevalue,String order);
	
	/** 
	 * 通过任意多个BO属性获得记录 
	 * @param String boname,BO的名字
	 * @param String[] attributename,多个BO属性的名字
	 * @param Object[] attributevalue,多个BO的属性的值 
	 */ 
	public List findByAny(String boname,String[] attributename,Object[] attributevalue,String order);
	
	/** 
	 * 通过任意多个BO属性获得记录 
	 * @param String boname,BO的名字
	 * @param String[] attributename,多个BO属性的名字
	 * @param Object[] attributevalue,多个BO的属性的值 
	 */ 
	public List findByAnyString(String boname,String[] attributename,String[] attributevalue,String order);
	
	/** 
	 * 获得所有记录 
	 * @param String boname,BO的名字
	 */ 
	public List findAll(String boname);
	
	/** 
	 * 获得记录总数 
	 * @param String boname,BO的名字 
	 */ 
	public Integer getBOCount(String boname);
	
	/** 
	 * 根据属性条件获得记录总数 
	 * @param String boname,BO的名字 
	 */ 
	public Integer getBOCount(String boname,String attributename,Object attributevalue);
	
	/** 
	 * 获得排序hql字符串 
	 * @param String boname,BO的名字
	 * @param String attributename,需要排序的BO属性名字 
	 * @param int order,升序为0，降序为1
	 */ 
	public String getOrderString(String attributename,int order );
	
	/** 
	 * 获得排序hql字符串 
	 * @param String boname,BO的名字
	 * @param String attributename,需要排序的BO属性名字 
	 * @param int order,升序为0，降序为1
	 */ 
	public String getOrderString(String[] attributenames,int[] orders );
	
	/** 
	 * 获得分组hql字符串 
	 * @param String boname,BO的名字
	 * @param String attributename,需要排序的BO属性名字 
	 */ 
	//public String getGroupString(String attributename);
	
	/** 
	 * 通过引用查询得到查询结果 
	 * @param String queryName，查询名字 
	 */
	public List findByNamedQuery(String queryName);
	
	/** 
	 * 通过引用查询得到查询结果 
	 * @param String queryName,查询名字
	 * @param String paramName,参数的名字 
	 * @param String value,参数的值 
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value);
	
	/** 
	 * 通过引用查询得到查询结果 
	 * @param String queryName，查询名字
	 * @param String[] paramName,参数名字的数组 
	 * @param String[] value,参数值的数组 
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values);
	
	/** 
	 * 执行批量删除操作 
	 * @param String boname，BO的名字
	 * @param String attributename,删除条件：BO属性名字 
	 * @param Object attributevalue,删除条件：BO属性值 
	 */
	public Integer bulkDelete(String boname,String attributename,Object attributevalue);
	

	/** 
	 * 执行批量修改操作 
	 * @param String boname，BO的名字
	 * @param String attributename,需要修改的BO属性名字 
	 * @param Object attributevalue,修改后的BO属性值
	 * @param String condition,修改条件 
	 */
	public Integer bulkUpdate(String boname,String setattributename,Object setattributevalue,String conditon);
	
	/** 
	 * 获得延迟加载数据 
	 * @param Object object，需要加载的对象
	 */
	public void getLazyData(Object object);

	/** 
	 * 分页查询 
	 * @param String queryString，查询语句
	 * @param Object[] parameters, 查询参数 
	 * @param PageInfo pageInfo, 分页信息
	 */
	public List findPageByQuery(final String queryString, final Object[] parameters, final PageInfo pageInfo);

	public List findPageByHqlQuery(final String queryString, final Object[] parameters,final PageInfo pageInfo);

	/** 
	 *使用原始SQL语句查询 
	 * @param String queryString，查询语句
	 * @param Object[] parameters, 查询参数 
	 */
	public List findBySQLQuery(final String queryString, final Object[] parameters);
	
	public boolean querySql(String sqlStr);
	
	public HibernateTemplate getHibernateTemplate();
}
