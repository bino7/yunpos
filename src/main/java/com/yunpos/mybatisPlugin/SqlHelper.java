package com.yunpos.mybatisPlugin;

import com.yunpos.model.SysDataRule;

public class SqlHelper {
	 private static final ThreadLocal<SysDataRule> ORDER_BY = new ThreadLocal<SysDataRule>();

	    public static SysDataRule getOrderBy() {
	    	SysDataRule orderBy = ORDER_BY.get();
	        return orderBy;
	    }

	    /**
	     * 增加排序
	     *
	     * @param orderBy
	     */
	    public static void orderBy(SysDataRule orderBy) {
	        ORDER_BY.set(orderBy);
	    }

	    /**
	     * 清除本地变量
	     */
	    public static void clear() {
	        ORDER_BY.remove();
	    }
}
