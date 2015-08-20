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

package com.yunpos.persistence.dao;


import com.yunpos.rewriter.filter.Filter;

public interface FilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(com.yunpos.model.Filter record);

    int insertSelective(com.yunpos.model.Filter record);

    Filter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(com.yunpos.model.Filter record);

    int updateByPrimaryKey(com.yunpos.model.Filter record);
}