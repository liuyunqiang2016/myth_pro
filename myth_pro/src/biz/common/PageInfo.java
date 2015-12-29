package biz.common;

public class PageInfo {
	/**
	 * 总页数
	 */
	private int totalPage = 1;

	/**
	 * 前一页
	 */
	private int prePage = 1;

	/**
	 * 下一页
	 */
	private int nextPage = 1;

	/**
	 * 总记录数
	 */
	private int totalRec = 0;

	/**
	 * 默认每页记录数
	 */
	private final int defaultPageSize = 10;

	/**
	 * 每页记录数
	 */
	private int pageSize = defaultPageSize;

	/**
	 * 当前页码
	 */
	private int pageIndex = 1;

	/**
	 * 全部页码，从1开始
	 */
	private int[] pageNumbers;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex > 0 ? pageIndex : 1;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage > this.totalPage ? this.totalPage : nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize > 0 ? pageSize : 10;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage < 1 ? 1 : prePage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage > 0 ? totalPage : 1;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec > -1 ? totalRec : 0;
	}

	public int[] getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(int[] pageNumbers) {
		this.pageNumbers = pageNumbers;
	}
}
