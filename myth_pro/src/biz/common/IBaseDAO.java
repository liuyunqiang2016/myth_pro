package biz.common;

import java.util.List;


public interface IBaseDAO
{
	public IModel save(IModel model);
	public void delByObj(IModel model);
	public IModel updByObj(IModel model);
	public IModel getObj(IModel model);
	public List getList();
	public List getList(PageInfo pageInfo);
	public List findByList(IModel model);
	public List findByList(IModel model,PageInfo pageInfo);

}
