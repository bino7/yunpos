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

import com.yunpos.rewriter.node.Column;
import com.yunpos.rewriter.node.AliasTable;
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
public class ColumnNameBuilder implements NodeBuilder {
    private static final int maxText=3;
    private List<String> textList;
    public ColumnNameBuilder(){
        textList=new ArrayList<>();
    }
    @Override
    public void push(String text) {
        if(textList.size()>maxText){
            String curTexts= StringUtils.join(textList," ");
            throw new java.lang.IllegalArgumentException("exceeds the expected maximum size of the input " +
                    "text "+this.getClass()+" current texts "+curTexts);
        }
        textList.add(text);
    }

    @Override
    public Column result() {
        if(textList.size()==2){
            throw new java.lang.IllegalArgumentException("unexpected size of input text 2,expected 1 or 3 "+
            this.getClass());
        }
        Column columnName=new Column();
        if(textList.size()==3){
            //has table alias
            columnName.setTable(new AliasTable(textList.get(0)));
            columnName.setColumnName(textList.get(2));
        }else{
            columnName.setColumnName(textList.get(0));
        }
        return columnName;
    }
}
