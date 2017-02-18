package com.power.spring.lesson8.annotationTransaction.dao;

import com.power.spring.lesson8.annotationTransaction.model.OrderInfo;
import com.power.spring.lesson8.annotationTransaction.model.OrderInfoExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface OrderInfoMapper {
    @SelectProvider(type=OrderInfoSqlProvider.class, method="countByExample")
    long countByExample(OrderInfoExample example);

    @DeleteProvider(type=OrderInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderInfoExample example);

    @Delete({
        "delete from orders",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into orders (id, product_name, ",
        "price, user_id)",
        "values (#{id,jdbcType=INTEGER}, #{productName,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=DOUBLE}, #{userId,jdbcType=INTEGER})"
    })
    int insert(OrderInfo record);

    @InsertProvider(type=OrderInfoSqlProvider.class, method="insertSelective")
    int insertSelective(OrderInfo record);

    @SelectProvider(type=OrderInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<OrderInfo> selectByExample(OrderInfoExample example);

    @Select({
        "select",
        "id, product_name, price, user_id",
        "from orders",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="product_name", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    OrderInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    @UpdateProvider(type=OrderInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderInfo record);

    @Update({
        "update orders",
        "set product_name = #{productName,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=DOUBLE},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderInfo record);
}