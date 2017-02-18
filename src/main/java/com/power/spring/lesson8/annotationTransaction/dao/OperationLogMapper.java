package com.power.spring.lesson8.annotationTransaction.dao;

import com.power.spring.lesson8.annotationTransaction.model.OperationLog;
import com.power.spring.lesson8.annotationTransaction.model.OperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OperationLogMapper {
    @SelectProvider(type=OperationLogSqlProvider.class, method="countByExample")
    long countByExample(OperationLogExample example);

    @DeleteProvider(type=OperationLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(OperationLogExample example);

    @Delete({
        "delete from t_oplogs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_oplogs (id, operator, ",
        "operation, op_time, ",
        "op_id)",
        "values (#{id,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{operation,jdbcType=VARCHAR}, #{opTime,jdbcType=TIMESTAMP}, ",
        "#{opId,jdbcType=INTEGER})"
    })
    int insert(OperationLog record);

    @InsertProvider(type=OperationLogSqlProvider.class, method="insertSelective")
    int insertSelective(OperationLog record);

    @SelectProvider(type=OperationLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_time", property="opTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="op_id", property="opId", jdbcType=JdbcType.INTEGER)
    })
    List<OperationLog> selectByExample(OperationLogExample example);

    @Select({
        "select",
        "id, operator, operation, op_time, op_id",
        "from t_oplogs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_time", property="opTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="op_id", property="opId", jdbcType=JdbcType.INTEGER)
    })
    OperationLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OperationLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    @UpdateProvider(type=OperationLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OperationLog record, @Param("example") OperationLogExample example);

    @UpdateProvider(type=OperationLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OperationLog record);

    @Update({
        "update t_oplogs",
        "set operator = #{operator,jdbcType=VARCHAR},",
          "operation = #{operation,jdbcType=VARCHAR},",
          "op_time = #{opTime,jdbcType=TIMESTAMP},",
          "op_id = #{opId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OperationLog record);
}