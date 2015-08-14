/*
 * *
 *  * 功能描述：
 *  * <p>
 *  * 版权所有：小牛信息科技有限公司
 *  * <p>
 *  * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *  *
 *  * @author Bino Zhong 新增日期：${date}
 *  * @author Bino Zhong 修改日期：${date}
 *  *
 *
 */

package com.yunpos.rewriter;

import com.yunpos.rewriter.filter.Filter;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/6
 * @author bino 修改日期：2015/8/6
 */
public interface StatementRewriter {
    RewritenStatement rewrite(String sql,Filter filter,Binding binding);
}
