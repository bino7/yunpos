package com.yunpos.persistence.dao;

import com.yunpos.model.accessory.Accessory;

/**
 * 附件图片文件
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author tiger_lin 新增日期：2015年9月1日
 * @author tiger_lin 修改日期：2015年9月1日
 *
 */
public interface AccessoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Accessory record);

    int insertSelective(Accessory record);

    Accessory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Accessory record);

    int updateByPrimaryKey(Accessory record);
}