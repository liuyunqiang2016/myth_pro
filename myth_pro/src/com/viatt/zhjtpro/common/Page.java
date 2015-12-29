package com.viatt.zhjtpro.common;

import java.util.List;

public class Page
{

	private String whereClause;

	private int start;

	private int numberPerPage;

	private List list;

	private int totalNumber;

	private int isNewQuery;

	private int toPage;

	public Page()
	{ 
		this(0, 10, null);
	}

	public Page(int start, int numberPerPage, List list)
	{
		whereClause = "";
		this.start = 0;
		this.numberPerPage = 10;
		this.list = null;
		totalNumber = 0;
		isNewQuery = 0;
		toPage = 0;
		this.start = start;
		this.numberPerPage = numberPerPage != 0 ? numberPerPage : 10;
		this.list = list;
	}
	
	public int getCurrentPageSequence()
	{
		int currentPage = (start + 1) / numberPerPage;
		return (start + 1) % numberPerPage != 0 ? currentPage + 1 : currentPage;
	}

	public int getNextPage()
	{
		return getCurrentPageSequence() + 1;
	}

	public int getPrevious()
	{
		return getCurrentPageSequence() <= 1 ? 1 : getCurrentPageSequence() - 1;
	}

	public List getList()
	{
		return list;
	}

	public void setList(List list)
	{
		this.list = list;
	}

	public int getNumberPerPage()
	{
		return numberPerPage;
	}

	public void setNumberPerPage(int numberPerPage)
	{
		this.numberPerPage = numberPerPage != 0 ? numberPerPage : 10;
	}

	public int getStart()
	{
		if (start == 0 && toPage > 0)
			if (toPage > getTotalPage())
			{
				if (getTotalPage() <= 0)
					start = 0;
				else
					start = (getTotalPage() - 1) * numberPerPage;
			} else
			{
				start = (toPage - 1) * numberPerPage;
			}
		return start;
	}

	public void setStart(int start)
	{
		if (start < 0)
			start = 0;
		this.start = start;
	}

	public int getTotalNumber()
	{
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber)
	{
		this.totalNumber = totalNumber;
	}

	public String getWhereClause()
	{
		return whereClause;
	}

	public void setWhereClause(String whereClause)
	{
		this.whereClause = whereClause;
	}

	public int getTotalPage()
	{
		return getTotalNumber() % numberPerPage != 0 ? getTotalNumber()
				/ numberPerPage + 1 : getTotalNumber() / numberPerPage;
	}

	public int getCurrentPageLastRow()
	{
		return start + (list == null ? 0 : list.size());
	}

	public int getNextStart()
	{
		if (totalNumber - numberPerPage < 0)
			return 0;
		else
			return start + numberPerPage >= totalNumber ? totalNumber
					- (totalNumber % numberPerPage != 0 ? totalNumber
							% numberPerPage : numberPerPage) : start
					+ numberPerPage;
	}

	public int getProviousStart()
	{
		return start - numberPerPage <= 0 ? 0 : start - numberPerPage;
	}

	public int getLastPageStart()
	{
		return (getTotalPage() - 1) * numberPerPage <= 0 ? 0
				: (getTotalPage() - 1) * numberPerPage;
	}

	public int getIsNewQuery()
	{
		return isNewQuery;
	}

	public void setIsNewQuery(int isNewQuery)
	{
		this.isNewQuery = isNewQuery;
	}

	public int getToPage()
	{
		return toPage;
	}

	public void setToPage(int toPage)
	{
		if (toPage < 0)
			toPage = 0;
		this.toPage = toPage;
	}
}
