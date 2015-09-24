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

package com.yunpos.rewriter.filter;

import com.yunpos.rewriter.value.Value;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/13
 * @author bino 修改日期：2015/8/13
 */
public class ColumnFilter extends Filter {
    private String tableAlias,tableName,colName;
    public ColumnFilter(){};
    public ColumnFilter(Integer dataTypeCode,String json) throws IOException, ParseException {
        super(Value.DataType.fromCode(dataTypeCode),json);
    }
    public ColumnFilter(Value.DataType dataType,String json) throws IOException, ParseException {
        super(dataType,json);
    }

    @Override
    protected String getFilterKey() {
        return tableAlias!=null?tableAlias+"."+colName:colName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    @Override
    public String toString() {
        return super.toString()+" ColumnFilter{" +
                "tableAlias='" + tableAlias + '\'' +
                ", tableName='" + tableName + '\'' +
                ", colName='" + colName + '\'' +
                '}';
    }
}
