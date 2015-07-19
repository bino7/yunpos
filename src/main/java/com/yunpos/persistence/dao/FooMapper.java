package com.yunpos.persistence.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yunpos.model.Foo;

public interface FooMapper {
	@Select("select * from Foo where id = #{id}")
    Foo findOne(int id);
    @Select("select * from Foo")
    List<Foo> findAll();
    @Insert("insert into foo (id, name) values (#{id}, #{name})")
    public int create(Foo foo);
    @Delete("delete from foo")
    public void deleteAll();
}
