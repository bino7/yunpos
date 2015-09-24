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

import com.yunpos.rewriter.value.SysParamValue;

import java.io.IOException;
import java.text.ParseException;

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
public class KeyFilter extends Filter {
    private String tableAlias,table,associatedColumn,keyTableAlias,keyTable,keyColumn, keyPrimaryColumn;
    private SysParamValue sysValue;
    public KeyFilter(Integer dataTypeCode,String key,String json) throws IOException, ParseException {
        super(dataTypeCode,json);
        sysValue=new SysParamValue(getDataType(),key);
        //setFilterValue(sysValue);
    }

    @Override
    protected String getFilterKey() {
        return (tableAlias!=null?tableAlias+".":"")+keyTable+"."+keyColumn;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAssociatedColumn() {
        return associatedColumn;
    }

    public void setAssociatedColumn(String associatedColumn) {
        this.associatedColumn = associatedColumn;
    }

    public String getKeyTable() {
        return keyTable;
    }

    public void setKeyTable(String keyTable) {
        this.keyTable = keyTable;
    }

    public String getKeyTableAlias() {
        return keyTableAlias;
    }

    public void setKeyTableAlias(String keyTableAlias) {
        this.keyTableAlias = keyTableAlias;
    }

    public String getKeyPrimaryColumn() {
        return keyPrimaryColumn;
    }

    public void setKeyPrimaryColumn(String keyPrimaryColumn) {
        this.keyPrimaryColumn = keyPrimaryColumn;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public void setOp(Op op) {
        super.setOp(op);
        //TODO
        /*if(op!=Op.EQ && op!=Op.IN){
            throw new IllegalArgumentException("filter(name:"+getName()+")'s" +
                    " Op must be EQ or IN current Op is "+op.getCode()+" "+Op.EQ.getCode()+" "+Op.IN.getCode());
        }*/
    }

    @Override
    public String toString() {
        return super.toString()+" KeyFilter{" +
                "sysValue=" + sysValue +
                '}';
    }
}
