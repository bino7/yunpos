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

package com.yunpos.rewriter.node.builder;

import com.yunpos.rewriter.node.BaseNode;
import com.yunpos.rewriter.node.Table;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/12
 * @author bino 修改日期：2015/8/12
 */
public class TableBuilder implements NodeBuilder{
    private static final int maxText=3;
    private List<String> textList;

    public TableBuilder(){
        textList=new ArrayList<>();
    }

    @Override
    public void push(String text) {
        if(textList.size()>maxText){
            String curTexts= StringUtils.join(textList, " ");
            throw new IllegalArgumentException("exceeds the expected maximum size of the input " +
                    "text "+this.getClass()+" current texts "+curTexts);
        }
        textList.add(text);
    }

    @Override
    public Table result() {
        Table table=new Table();
        if(textList.size()==3){
            //has table alias
            table.setTableName(textList.get(0));
            table.setTableAlias(textList.get(2));
        }else if(textList.size()==2){
            table.setTableName(textList.get(0));
            table.setTableAlias(textList.get(1));
        }else{
            table.setTableName(textList.get(0));
        }
        return table;
    }
}
