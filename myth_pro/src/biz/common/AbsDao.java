package biz.common;

public abstract class AbsDao
{
	private IDao daoFactory;

	public IDao getDaoFactory()
	{
		return daoFactory;
	}

	public void setDaoFactory(IDao daoFactory)
	{
		this.daoFactory = daoFactory;
	}
}
