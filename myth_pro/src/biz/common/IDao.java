package biz.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * <p>Title: </p>
 * <p>Description: DAO�ӿ�</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */
public interface IDao {
	/** 
	 * ���Ӽ�¼ 
	 * @param Object entity,��Ҫ�����BO
	 */ 
	public Object save(Object entity);
	
	/** 
	 * ���ӻ��޸ļ�¼ 
	 * @param Object entity,��Ҫ�����BO
	 */ 
	public Object saveOrUpdate(Object entity);
	
	/** 
	 * ɾ����¼ 
	 * @param Object entity,��Ҫɾ����BO 
	 */ 
	public void delete(Object entity);
	
	/** 
	 * ����idɾ����¼ 
	 * @param Class entityClass,BO������ 
	 * @param Serializable id,BO��idֵ
	 */ 
	public void deleteById(Class entityClass, Serializable id);
	
	/** 
	 * ɾ�����м�¼ 
	 * @param Collection entities,BO�ļ��� 
	 */ 
	public void deleteAll(Collection entities);
	
	/** 
	 * �޸ļ�¼ 
	 * @param Object,Ҫ�޸ĵ�BO 
	 */ 
	public void update(Object entity);
	
	/** 
	 * ͨ��id��ü�¼ 
	 * @param Serializable id,BO��idֵ 
	 */ 
	public Object findById(Class entityClass,Serializable id);
	
	/** 
	 * ͨ������һ��BO���Ի�ü�¼ 
	 * @param String boname,BO������
	 * @param String attributename,BO���Ե�����
	 * @param Object attributevalue,BO�����Ե�ֵ 
	 */ 
	public List findByAny(String boname,String attributename,Object attributevalue,String order);
	
	/** 
	 * ͨ������һ��BO���Ի�ü�¼ 
	 * @param String boname,BO������
	 * @param String attributename,BO���Ե�����
	 * @param Object attributevalue,BO�����Ե�ֵ 
	 */ 
	public List findByAnyString(String boname,String attributename,String attributevalue,String order);
	
	/** 
	 * ͨ��������BO���Ի�ü�¼ 
	 * @param String boname,BO������
	 * @param String[] attributename,���BO���Ե�����
	 * @param Object[] attributevalue,���BO�����Ե�ֵ 
	 */ 
	public List findByAny(String boname,String[] attributename,Object[] attributevalue,String order);
	
	/** 
	 * ͨ��������BO���Ի�ü�¼ 
	 * @param String boname,BO������
	 * @param String[] attributename,���BO���Ե�����
	 * @param Object[] attributevalue,���BO�����Ե�ֵ 
	 */ 
	public List findByAnyString(String boname,String[] attributename,String[] attributevalue,String order);
	
	/** 
	 * ������м�¼ 
	 * @param String boname,BO������
	 */ 
	public List findAll(String boname);
	
	/** 
	 * ��ü�¼���� 
	 * @param String boname,BO������ 
	 */ 
	public Integer getBOCount(String boname);
	
	/** 
	 * ��������������ü�¼���� 
	 * @param String boname,BO������ 
	 */ 
	public Integer getBOCount(String boname,String attributename,Object attributevalue);
	
	/** 
	 * �������hql�ַ��� 
	 * @param String boname,BO������
	 * @param String attributename,��Ҫ�����BO�������� 
	 * @param int order,����Ϊ0������Ϊ1
	 */ 
	public String getOrderString(String attributename,int order );
	
	/** 
	 * �������hql�ַ��� 
	 * @param String boname,BO������
	 * @param String attributename,��Ҫ�����BO�������� 
	 * @param int order,����Ϊ0������Ϊ1
	 */ 
	public String getOrderString(String[] attributenames,int[] orders );
	
	/** 
	 * ��÷���hql�ַ��� 
	 * @param String boname,BO������
	 * @param String attributename,��Ҫ�����BO�������� 
	 */ 
	//public String getGroupString(String attributename);
	
	/** 
	 * ͨ�����ò�ѯ�õ���ѯ��� 
	 * @param String queryName����ѯ���� 
	 */
	public List findByNamedQuery(String queryName);
	
	/** 
	 * ͨ�����ò�ѯ�õ���ѯ��� 
	 * @param String queryName,��ѯ����
	 * @param String paramName,���������� 
	 * @param String value,������ֵ 
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value);
	
	/** 
	 * ͨ�����ò�ѯ�õ���ѯ��� 
	 * @param String queryName����ѯ����
	 * @param String[] paramName,�������ֵ����� 
	 * @param String[] value,����ֵ������ 
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] values);
	
	/** 
	 * ִ������ɾ������ 
	 * @param String boname��BO������
	 * @param String attributename,ɾ��������BO�������� 
	 * @param Object attributevalue,ɾ��������BO����ֵ 
	 */
	public Integer bulkDelete(String boname,String attributename,Object attributevalue);
	

	/** 
	 * ִ�������޸Ĳ��� 
	 * @param String boname��BO������
	 * @param String attributename,��Ҫ�޸ĵ�BO�������� 
	 * @param Object attributevalue,�޸ĺ��BO����ֵ
	 * @param String condition,�޸����� 
	 */
	public Integer bulkUpdate(String boname,String setattributename,Object setattributevalue,String conditon);
	
	/** 
	 * ����ӳټ������� 
	 * @param Object object����Ҫ���صĶ���
	 */
	public void getLazyData(Object object);

	/** 
	 * ��ҳ��ѯ 
	 * @param String queryString����ѯ���
	 * @param Object[] parameters, ��ѯ���� 
	 * @param PageInfo pageInfo, ��ҳ��Ϣ
	 */
	public List findPageByQuery(final String queryString, final Object[] parameters, final PageInfo pageInfo);

	public List findPageByHqlQuery(final String queryString, final Object[] parameters,final PageInfo pageInfo);

	/** 
	 *ʹ��ԭʼSQL����ѯ 
	 * @param String queryString����ѯ���
	 * @param Object[] parameters, ��ѯ���� 
	 */
	public List findBySQLQuery(final String queryString, final Object[] parameters);
	
	public boolean querySql(String sqlStr);
	
	public HibernateTemplate getHibernateTemplate();
}
