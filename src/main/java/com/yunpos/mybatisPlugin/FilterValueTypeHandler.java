package com.yunpos.mybatisPlugin;

import com.yunpos.model.FilterDifinition;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bino on 15-9-21.
 */
public class FilterValueTypeHandler extends BaseTypeHandler<FilterDifinition.ValueType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, FilterDifinition.ValueType type, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, type.getCode());
    }

    @Override
    public FilterDifinition.ValueType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Integer code = resultSet.getInt(columnName);
        return code == null?null: FilterDifinition.ValueType.fromCode(code);
    }

    @Override
    public FilterDifinition.ValueType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return code == null?null:FilterDifinition.ValueType.fromCode(code);
    }

    @Override
    public FilterDifinition.ValueType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return code == null?null: FilterDifinition.ValueType.fromCode(code);
    }
}
