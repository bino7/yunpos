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

package com.yunpos.mybatisPlugin;

import com.yunpos.rewriter.filter.Filter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @author bino 新增日期：2015/8/14
 * @author bino 修改日期：2015/8/14
 */
public class SupportOpHandler extends BaseTypeHandler<List<Filter.Op>> {
    private static Logger logger = LoggerFactory.getLogger(SupportOpHandler.class);
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Filter.Op> opList, JdbcType jdbcType) throws SQLException {
        logger.debug("SupportOpHandler setNonNullParameter");
        int code=0;
        for(Filter.Op op:opList){
            code=setOp(code,op.getCode());
        }
        ps.setInt(i, code);
    }

    @Override
    public List<Filter.Op> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        logger.debug("SupportOpHandler getNullableResult");
        Integer code = resultSet.getInt(columnName);
        if(code==null){
            return null;
        }
        List<Filter.Op> ret=new ArrayList<>();
        Filter.Op[] ops=Filter.Op.values();
        for(int i=0;i<ops.length;i++){
            if(isOp(code,ops[i].getCode())){
                ret.add(ops[i]);
            }
        }
        return ret;
    }

    @Override
    public List<Filter.Op> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        logger.debug("SupportOpHandler getNullableResult");
        Integer code = rs.getInt(columnIndex);
        if(code==null){
            return null;
        }
        List<Filter.Op> ret=new ArrayList<>();
        Filter.Op[] ops=Filter.Op.values();
        for(int i=0;i<ops.length;i++){
            if(isOp(code,ops[i].getCode())){
                ret.add(ops[i]);
            }
        }
        return ret;
    }

    @Override
    public List<Filter.Op> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        logger.debug("SupportOpHandler getNullableResult");
        Integer code = cs.getInt(columnIndex);
        if(code==null){
            return null;
        }
        List<Filter.Op> ret=new ArrayList<>();
        Filter.Op[] ops=Filter.Op.values();
        for(int i=0;i<ops.length;i++){
            if(isOp(code,ops[i].getCode())){
                ret.add(ops[i]);
            }
        }
        return ret;
    }

    private int setOp(int code,int opCode) {
        code |= 1 << opCode;
        return code;
    }

    private int unsetOp(int code,int opCode) {
        code &= ~(1 << opCode);
        return code;
    }

    private boolean isOp(int code,int opCode) {
        return (code >> opCode & 1) == 1;
    }
}
