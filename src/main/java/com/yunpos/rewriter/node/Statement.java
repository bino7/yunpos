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

package com.yunpos.rewriter.node;

import com.yunpos.rewriter.Binding;
import com.yunpos.rewriter.filter.ColumnFilter;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.filter.FilterGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

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
public class Statement extends RewritableNode implements NodeList {
    public static enum StatementType {
        SELECT,DELETE,INSERT,UPDATE;
    }
    private StatementType statementType;

    private List<Node> nodeList;
    private List<Column> columnList;
    private List<Table> tableList;
    private WhereClause whereClause;
    private String separator=" ";

    public Statement(StatementType statementType) {
        this.statementType = statementType;
        nodeList=new ArrayList<>();
        columnList=new ArrayList<>();
        tableList=new ArrayList<>();
    }

    //resolve column's table and table alias
    public void resolveNodes(){
        for(Column column:columnList){
            if (column.getTable()!=null && column.getTable() instanceof AliasTable){
                String tableAlias=column.getTable().getTableAlias();
                for(Table table:tableList){
                    if(tableAlias.equals(table.getTableAlias())){
                        column.setTable(table);
                        break;
                    }
                }
            }
        }

        int i=1;
        for(Table table:tableList){
            table.setTableAlias("t"+i);
            i++;
        }
    }

    public void addFilter(Filter filter,Binding binding){
        resolveFilterTableAlias(filter);
        String filterSql=filter.toSql(new HashMap<>());
        if (whereClause==null){
            whereClause=new WhereClause();
            whereClause.add(new BaseNode("where"));
        }
        Node filterNode=new BaseNode(" and ("+filterSql+")");
        whereClause.add(filterNode);

    }

    private void resolveFilterTableAlias(Filter filter){
        if(filter instanceof FilterGroup){
            resolveFilterGroupTableAlias((FilterGroup)filter);
        }
        if(filter instanceof ColumnFilter) {
            ColumnFilter columnFilter=(ColumnFilter)filter;
            for (Table table : tableList) {
                if (table.getTableName().equals(columnFilter.getTableName())) {
                    columnFilter.setTableAlias(table.getTableAlias());
                    break;
                }
            }
        }
    }

    private void resolveFilterGroupTableAlias(FilterGroup filterGroup){
        for(FilterGroup group:filterGroup.children){
            resolveFilterGroupTableAlias(group);
        }
        for(Filter filter:filterGroup.getFilterList()){
            resolveFilterTableAlias(filter);
        }
    }

    @Override
    public void add(Node node) {
        nodeList.add(node);
    }

    @Override
    public String getSeparator() {
        return separator;
    }

    @Override
    public String getText() {
        StringJoiner joiner=new StringJoiner(getSeparator());
        for(Node node:nodeList){
            joiner.add(node.getText());
        }
        return joiner.toString();
    }


    public StatementType getStatementType() {
        return statementType;
    }

    public void setStatementType(StatementType statementType) {
        this.statementType = statementType;
    }

    public WhereClause getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    public List<Column> getColumnList() {
        return columnList;
    }
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
    public List<Table> getTableList() {
        return tableList;
    }
    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
    public void addColumn(Column column){
        columnList.add(column);
    }
    public void addTable(Table table){
        tableList.add(table);
    }
    public void addNode(Node node){
        nodeList.add(node);
    }
    public List<Node> getNodeList(){
        return nodeList;
    }
}
