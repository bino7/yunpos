package com.yunpos.mybatisPlugin;

import com.yunpos.model.DataRule;

public class SqlHelper {
	 private static final ThreadLocal<DataRule> ORDER_BY = new ThreadLocal<DataRule>();

	    public static DataRule getOrderBy() {
	    	DataRule orderBy = ORDER_BY.get();
	        return orderBy;
	    }

	    /**
	     * 增加排序
	     *
	     * @param orderBy
	     */
	    public static void orderBy(DataRule orderBy) {
	        ORDER_BY.set(orderBy);
	    }

	    /**
	     * 清除本地变量
	     */
	    public static void clear() {
	        ORDER_BY.remove();
	    }
}
