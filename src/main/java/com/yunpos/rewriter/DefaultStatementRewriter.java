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

import com.yunpos.model.Resource;
import com.yunpos.rewriter.antlr.MySQLLexer;
import com.yunpos.rewriter.antlr.MySQLListener;
import com.yunpos.rewriter.antlr.MySQLParser;
import com.yunpos.rewriter.filter.FilterGroup;
import com.yunpos.rewriter.node.*;
import com.yunpos.rewriter.node.builder.BaseNodeBuilder;
import com.yunpos.rewriter.node.builder.ColumnNodeBuilder;
import com.yunpos.rewriter.node.builder.NodeBuilder;
import com.yunpos.rewriter.node.builder.TableBuilder;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Stack;

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
public class DefaultStatementRewriter implements StatementRewriter,MySQLListener {
    private static Logger logger = LoggerFactory.getLogger(DefaultStatementRewriter.class);
    private Statement rootStatement;
    private Stack<NodeList> nodeStack;
    private NodeBuilder curBuilder;
    private int i;
    public DefaultStatementRewriter(){
        nodeStack=new Stack<NodeList>();
    }

    @Override
    public String rewrite(String sql,Resource resource,Map<String,Object> params) {
        List<FilterGroup> filterGroupList=resource.getFilterGroupList();
        FilterGroup filter=new FilterGroup();
        filter.setChildren(filterGroupList);

        MySQLLexer lexer = new MySQLLexer(new ANTLRInputStream(sql));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySQLParser parser = new MySQLParser(tokens);
        parser.addParseListener(this);
        parser.stat();
        rootStatement.resolveNodes();
        if(filter!=null){
            rootStatement.addFilter(filter, params);
        }
        return rootStatement.getText();
    }


    public static void main(String[] args){
        /*FilterGroup filter=new FilterGroup();
        Filter filter1=new Filter();
        filter1.setLeftTable("city");
        filter1.setLeftParam("Name");
        filter1.setRightTable(null);
        filter1.setRightParams(new String[]{"N%"});
        filter1.setOp(Filter.Op.LIKE);

        Filter filter2=new Filter();
        filter2.setLeftTable("country");
        filter2.setLeftParam("Continent");
        filter2.setRightTable(null);
        filter2.setRightParams(new String[]{"'Europe'"});
        filter2.setOp(Filter.Op.EQ);

        List<Filter> andFilters= Arrays.asList(new Filter[]{filter1,filter2});
        filter.setFilterList(andFilters);

        FilterGroup filter3=new FilterGroup();
        Filter filter4=new Filter();
        filter4.setLeftTable("country");
        filter4.setLeftParam("Continent");
        filter4.setRightTable(null);
        filter4.setRightParams(new String[]{"'Asia'"});
        filter4.setOp(Filter.Op.EQ);

        Filter filter5=new Filter();
        filter5.setLeftTable("country");
        filter5.setLeftParam("LifeExpectancy");
        filter5.setRightTable(null);
        filter5.setRightParams(new String[]{"50"});
        filter5.setOp(Filter.Op.GT);
        List<Filter> andFilters2= Arrays.asList(new Filter[]{filter4,filter5});
        filter3.setFilterList(andFilters2);

        filter.getChildren().add(filter3);*/


        DefaultStatementRewriter rewriter=new DefaultStatementRewriter();
        rewriter.rewrite("select count(*) from city t1 " +
                "left outer join country t2 on t1.CountryCode=t2.Code " +
                "where t1.Population>100000", null,null);
        Statement statement=rewriter.getStatement();
        logger.debug(rewriter.getStatement().getText());
    }

    private void finishBuilder() {
        if(curBuilder!=null){
            finishBuilder(curBuilder);
            curBuilder=null;
        }
    }
    private void finishBuilder(NodeBuilder builder){
        finishBuilder(nodeStack.peek(), builder);
    }
    private void finishBuilder(NodeList nodeList,NodeBuilder builder){
        Node node=builder.result();
        if(node instanceof Table){
            Table table=(Table)node;
            table.setIndex(nodeList.size());
            rootStatement.addTable(table);
        }else if (node instanceof Column){
            rootStatement.addColumn((Column)node);
        }
        nodeList.add(node);

    }
    @Override
    public void enterExpr(MySQLParser.ExprContext ctx) {

    }

    @Override
    public void exitExpr(MySQLParser.ExprContext ctx) {

    }

    @Override
    public void enterTerm(MySQLParser.TermContext ctx) {
    }

    @Override
    public void exitTerm(MySQLParser.TermContext ctx) {

    }

    @Override
    public void enterAtom(MySQLParser.AtomContext ctx) {

    }

    @Override
    public void exitAtom(MySQLParser.AtomContext ctx) {

    }

    @Override
    public void enterQuoted_variable(MySQLParser.Quoted_variableContext ctx) {

    }

    @Override
    public void exitQuoted_variable(MySQLParser.Quoted_variableContext ctx) {

    }

    @Override
    public void enterNumber(MySQLParser.NumberContext ctx) {

    }

    @Override
    public void exitNumber(MySQLParser.NumberContext ctx) {

    }

    @Override
    public void enterNeg_number(MySQLParser.Neg_numberContext ctx) {

    }

    @Override
    public void exitNeg_number(MySQLParser.Neg_numberContext ctx) {

    }

    @Override
    public void enterFunction(MySQLParser.FunctionContext ctx) {
        finishBuilder();
        Function function=new Function();
        nodeStack.push(function);
    }

    @Override
    public void exitFunction(MySQLParser.FunctionContext ctx) {
        finishBuilder();
        Node node=nodeStack.pop();
        nodeStack.peek().add(node);
    }

    @Override
    public void enterFunction_name(MySQLParser.Function_nameContext ctx) {

    }

    @Override
    public void exitFunction_name(MySQLParser.Function_nameContext ctx) {
        finishBuilder();
    }



    @Override
    public void enterStat(MySQLParser.StatContext ctx) {
        logger.debug("begin");
    }

    @Override
    public void exitStat(MySQLParser.StatContext ctx) {
        if(curBuilder!=null){
            finishBuilder(rootStatement,curBuilder);
            curBuilder=null;
        }
    }

    @Override
    public void enterSchema_name(MySQLParser.Schema_nameContext ctx) {

    }

    @Override
    public void exitSchema_name(MySQLParser.Schema_nameContext ctx) {

    }

    @Override
    public void enterSelect_clause(MySQLParser.Select_clauseContext ctx) {
        rootStatement =new Statement(Statement.StatementType.SELECT);
        nodeStack.push(rootStatement);
    }

    @Override
    public void exitSelect_clause(MySQLParser.Select_clauseContext ctx) {
        nodeStack.pop();
    }



    @Override
    public void enterDelect_clause(MySQLParser.Delect_clauseContext ctx) {
        rootStatement =new Statement(Statement.StatementType.DELETE);
    }

    @Override
    public void exitDelect_clause(MySQLParser.Delect_clauseContext ctx) {
    }

    @Override
    public void enterInsert_clause(MySQLParser.Insert_clauseContext ctx) {
        rootStatement =new Statement(Statement.StatementType.INSERT);
    }

    @Override
    public void exitInsert_clause(MySQLParser.Insert_clauseContext ctx) {

    }

    @Override
    public void enterUpdate_clause(MySQLParser.Update_clauseContext ctx) {
        rootStatement =new Statement(Statement.StatementType.UPDATE);
    }

    @Override
    public void exitUpdate_clause(MySQLParser.Update_clauseContext ctx) {
    }

    @Override
    public void enterTable_name(MySQLParser.Table_nameContext ctx) {
        if(curBuilder!=null){
            finishBuilder(curBuilder);
        }
        curBuilder=new TableBuilder();
    }

    @Override
    public void exitTable_name(MySQLParser.Table_nameContext ctx) {
        assert curBuilder!=null;
        finishBuilder(curBuilder);
        curBuilder=null;
    }

    @Override
    public void enterTable_alias(MySQLParser.Table_aliasContext ctx) {

    }

    @Override
    public void exitTable_alias(MySQLParser.Table_aliasContext ctx) {
    }

    @Override
    public void enterValue(MySQLParser.ValueContext ctx) {

    }

    @Override
    public void exitValue(MySQLParser.ValueContext ctx) {

    }

    @Override
    public void enterValues_list_clause(MySQLParser.Values_list_clauseContext ctx) {

    }

    @Override
    public void exitValues_list_clause(MySQLParser.Values_list_clauseContext ctx) {

    }

    @Override
    public void enterColumn(MySQLParser.ColumnContext ctx) {

    }

    @Override
    public void exitColumn(MySQLParser.ColumnContext ctx) {

    }

    @Override
    public void enterColumn_name(MySQLParser.Column_nameContext ctx) {
        if(curBuilder!=null){
            finishBuilder(curBuilder);
        }
        curBuilder=new ColumnNodeBuilder();
    }

    @Override
    public void exitColumn_name(MySQLParser.Column_nameContext ctx) {
        assert curBuilder!=null;
        finishBuilder(curBuilder);
        curBuilder=null;
    }

    @Override
    public void enterAll_coumn_name(MySQLParser.All_coumn_nameContext ctx) {

    }

    @Override
    public void exitAll_coumn_name(MySQLParser.All_coumn_nameContext ctx) {

    }

    @Override
    public void enterColumn_alias(MySQLParser.Column_aliasContext ctx) {
    }

    @Override
    public void exitColumn_alias(MySQLParser.Column_aliasContext ctx) {

    }

    @Override
    public void enterIndex_name(MySQLParser.Index_nameContext ctx) {

    }

    @Override
    public void exitIndex_name(MySQLParser.Index_nameContext ctx) {

    }

    @Override
    public void enterColumn_list(MySQLParser.Column_listContext ctx) {

    }

    @Override
    public void exitColumn_list(MySQLParser.Column_listContext ctx) {

    }

    @Override
    public void enterColumn_list_clause(MySQLParser.Column_list_clauseContext ctx) {
    }

    @Override
    public void exitColumn_list_clause(MySQLParser.Column_list_clauseContext ctx) {

    }

    @Override
    public void enterFrom_clause(MySQLParser.From_clauseContext ctx) {

    }

    @Override
    public void exitFrom_clause(MySQLParser.From_clauseContext ctx) {

    }

    @Override
    public void enterWhere_clause(MySQLParser.Where_clauseContext ctx) {
        finishBuilder();
        WhereClause where=new WhereClause();
        ((Statement)nodeStack.peek()).setWhereClause(where);
        nodeStack.push(where);
    }

    @Override
    public void exitWhere_clause(MySQLParser.Where_clauseContext ctx) {
        finishBuilder();
        Node node=nodeStack.pop();
        nodeStack.peek().add(node);
    }

    @Override
    public void enterExpression_clause(MySQLParser.Expression_clauseContext ctx) {

    }

    @Override
    public void exitExpression_clause(MySQLParser.Expression_clauseContext ctx) {

    }

    @Override
    public void enterExpression(MySQLParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(MySQLParser.ExpressionContext ctx) {

    }

    @Override
    public void enterQuoted_expression(MySQLParser.Quoted_expressionContext ctx) {

    }

    @Override
    public void exitQuoted_expression(MySQLParser.Quoted_expressionContext ctx) {

    }

    @Override
    public void enterRight_element(MySQLParser.Right_elementContext ctx) {

    }

    @Override
    public void exitRight_element(MySQLParser.Right_elementContext ctx) {

    }

    @Override
    public void enterLeft_element(MySQLParser.Left_elementContext ctx) {

    }

    @Override
    public void exitLeft_element(MySQLParser.Left_elementContext ctx) {

    }

    @Override
    public void enterTarget_element(MySQLParser.Target_elementContext ctx) {

    }

    @Override
    public void exitTarget_element(MySQLParser.Target_elementContext ctx) {

    }

    @Override
    public void enterRelational_op(MySQLParser.Relational_opContext ctx) {

    }

    @Override
    public void exitRelational_op(MySQLParser.Relational_opContext ctx) {

    }

    @Override
    public void enterExpr_op(MySQLParser.Expr_opContext ctx) {

    }

    @Override
    public void exitExpr_op(MySQLParser.Expr_opContext ctx) {

    }

    @Override
    public void enterBetween_op(MySQLParser.Between_opContext ctx) {

    }

    @Override
    public void exitBetween_op(MySQLParser.Between_opContext ctx) {

    }

    @Override
    public void enterIs_or_is_not(MySQLParser.Is_or_is_notContext ctx) {

    }

    @Override
    public void exitIs_or_is_not(MySQLParser.Is_or_is_notContext ctx) {

    }

    @Override
    public void enterSimple_expression(MySQLParser.Simple_expressionContext ctx) {

    }

    @Override
    public void exitSimple_expression(MySQLParser.Simple_expressionContext ctx) {

    }

    @Override
    public void enterTable_references(MySQLParser.Table_referencesContext ctx) {

    }

    @Override
    public void exitTable_references(MySQLParser.Table_referencesContext ctx) {

    }

    @Override
    public void enterTable_reference(MySQLParser.Table_referenceContext ctx) {

    }

    @Override
    public void exitTable_reference(MySQLParser.Table_referenceContext ctx) {

    }

    @Override
    public void enterTable_factor1(MySQLParser.Table_factor1Context ctx) {

    }

    @Override
    public void exitTable_factor1(MySQLParser.Table_factor1Context ctx) {

    }

    @Override
    public void enterTable_factor2(MySQLParser.Table_factor2Context ctx) {

    }

    @Override
    public void exitTable_factor2(MySQLParser.Table_factor2Context ctx) {

    }

    @Override
    public void enterTable_factor3(MySQLParser.Table_factor3Context ctx) {

    }

    @Override
    public void exitTable_factor3(MySQLParser.Table_factor3Context ctx) {

    }

    @Override
    public void enterTable_factor4(MySQLParser.Table_factor4Context ctx) {

    }

    @Override
    public void exitTable_factor4(MySQLParser.Table_factor4Context ctx) {

    }

    @Override
    public void enterTable_atom(MySQLParser.Table_atomContext ctx) {

    }

    @Override
    public void exitTable_atom(MySQLParser.Table_atomContext ctx) {

    }

    @Override
    public void enterJoin_clause(MySQLParser.Join_clauseContext ctx) {

    }

    @Override
    public void exitJoin_clause(MySQLParser.Join_clauseContext ctx) {

    }

    @Override
    public void enterJoin_condition(MySQLParser.Join_conditionContext ctx) {

    }

    @Override
    public void exitJoin_condition(MySQLParser.Join_conditionContext ctx) {

    }

    @Override
    public void enterIndex_hint_list(MySQLParser.Index_hint_listContext ctx) {

    }

    @Override
    public void exitIndex_hint_list(MySQLParser.Index_hint_listContext ctx) {

    }

    @Override
    public void enterIndex_options(MySQLParser.Index_optionsContext ctx) {

    }

    @Override
    public void exitIndex_options(MySQLParser.Index_optionsContext ctx) {

    }

    @Override
    public void enterIndex_hint(MySQLParser.Index_hintContext ctx) {

    }

    @Override
    public void exitIndex_hint(MySQLParser.Index_hintContext ctx) {

    }

    @Override
    public void enterIndex_list(MySQLParser.Index_listContext ctx) {

    }

    @Override
    public void exitIndex_list(MySQLParser.Index_listContext ctx) {

    }

    @Override
    public void enterPartition_clause(MySQLParser.Partition_clauseContext ctx) {

    }

    @Override
    public void exitPartition_clause(MySQLParser.Partition_clauseContext ctx) {

    }

    @Override
    public void enterPartition_names(MySQLParser.Partition_namesContext ctx) {

    }

    @Override
    public void exitPartition_names(MySQLParser.Partition_namesContext ctx) {

    }

    @Override
    public void enterPartition_name(MySQLParser.Partition_nameContext ctx) {

    }

    @Override
    public void exitPartition_name(MySQLParser.Partition_nameContext ctx) {

    }

    @Override
    public void enterSubquery_alias(MySQLParser.Subquery_aliasContext ctx) {

    }

    @Override
    public void exitSubquery_alias(MySQLParser.Subquery_aliasContext ctx) {

    }

    @Override
    public void enterSubquery(MySQLParser.SubqueryContext ctx) {

    }

    @Override
    public void exitSubquery(MySQLParser.SubqueryContext ctx) {

    }

    @Override
    public void enterOffset(MySQLParser.OffsetContext ctx) {

    }

    @Override
    public void exitOffset(MySQLParser.OffsetContext ctx) {

    }

    @Override
    public void enterRow_count(MySQLParser.Row_countContext ctx) {

    }

    @Override
    public void exitRow_count(MySQLParser.Row_countContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
        if(curBuilder==null){
            curBuilder=new BaseNodeBuilder();
        }
        curBuilder.push(terminalNode.getText());
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {


    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {
    }


    public Statement getStatement(){
        return rootStatement;
    }
}
