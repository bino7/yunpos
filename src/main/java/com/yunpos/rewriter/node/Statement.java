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

import com.yunpos.rewriter.filter.ColumnFilter;
import com.yunpos.rewriter.filter.Filter;
import com.yunpos.rewriter.filter.FilterGroup;
import com.yunpos.rewriter.filter.KeyFilter;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Consumer;

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
        columnList.stream().filter(c->c.getTable()!=null && c.getTable() instanceof AliasTable)
                .forEach(c -> {
                            String tableAlias = c.getTable().getTableAlias();
                            tableList.stream().filter(t -> tableAlias.equals(t.getTableAlias()))
                                    .forEach(t -> c.setTable(t));
                        }
                );
        int i=1;
        for(Table table:tableList){
            table.setTableAlias("t"+i);
            i++;
        }
    }

    public void addFilter(Filter filter,Map<String,Object> params){
        //resolveKeyFilterTable
        new FilterWalker(f->{
            if (f instanceof KeyFilter){
                KeyFilter keyFilter=(KeyFilter)f;
                String tableName=keyFilter.getTable();
                if(tableList.stream().anyMatch(t -> t.getTableName().equals(tableName))==false){
                    Table firstTable=tableList.get(0);
                    int index=firstTable.getIndex();
                    Table table=new Table();
                    table.setIndex(index + 2);
                    table.setTableName(tableName);
                    nodeList.add(index+1,new BaseNode(","));
                    nodeList.add(index+2,table);
                    tableList.add(1,table);
                    for(int i=2;i<tableList.size();i++){
                        Table t=tableList.get(i);
                        t.setIndex(t.getIndex()+2);
                    }
                }
            }
        }).walk(filter);

        //resolveFilterTableAlias
        new FilterWalker(f->{
            if(f instanceof ColumnFilter) {
                ColumnFilter columnFilter=(ColumnFilter)filter;
                for (Table table : tableList) {
                    if (table.getTableName().equals(columnFilter.getTableName())) {
                        columnFilter.setTableAlias(table.getTableAlias());
                        break;
                    }
                }
            }

            if(f instanceof KeyFilter) {
                KeyFilter keyFilter=(KeyFilter)f;
                for (Table table : tableList) {
                    if (table.getTableName().equals(keyFilter.getTable())) {
                        keyFilter.setTableAlias(table.getTableAlias());
                        break;
                    }
                    if(table.getTableName().equals(keyFilter.getKeyTable())){
                        keyFilter.setKeyTableAlias(table.getTableAlias());
                        break;
                    }
                }
            }

        }).walk(filter);

        filter.bind(params);
        //resolve key filter table associations
        Set<String> associations=new TreeSet<>();
        new FilterWalker(f->{
            if(f instanceof KeyFilter){
                KeyFilter kf=(KeyFilter)f;
                associations.add(kf.getTableAlias()+"."+kf.getTable()+"."+kf.getAssociatedColumn()+"="
                        +kf.getKeyTableAlias()+"."+kf.getKeyTable()+"."+kf.getKeyPrimaryColumn());
            }
        }).walk(filter);
        String associationSql=StringUtils.join(associations, "and");
        String filterSql=filter.toSql();
        if (whereClause==null){
            whereClause=new WhereClause();
            whereClause.add(new BaseNode("where"));
        }
        Node associationNode=new BaseNode(" and "+associationSql);
        whereClause.add(associationNode);
        Node filterNode=new BaseNode(" and ("+filterSql+")");
        whereClause.add(filterNode);
    }



    private class FilterWalker{
        private Consumer<Filter> filterConsumer;

        public FilterWalker(Consumer<Filter> filterConsumer) {
            this.filterConsumer = filterConsumer;
        }

        public void walk(Filter filter) {
            if(filter instanceof FilterGroup){
                FilterGroup filteGroup=(FilterGroup)filter;
                filteGroup.getFilterList().forEach(f->filterConsumer.accept(f));
                filteGroup.getChildren().forEach(g->walk(g));
            }else{
                filterConsumer.accept(filter);
            }
        }

    }

    @Override
    public void add(Node node) {
        nodeList.add(node);
    }

    @Override
    public int size() {
        return nodeList.size();
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
