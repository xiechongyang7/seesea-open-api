package com.seesea.seeseagateway.mapper;

import com.seesea.seeseagateway.entity.GatewayLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GatewayLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Mon Nov 30 21:09:52 CST 2020
     */
    int deleteByPrimaryKey(@Param("reqId") String reqId, @Param("sequenceId") String sequenceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Mon Nov 30 21:09:52 CST 2020
     */
    int insert(GatewayLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Mon Nov 30 21:09:52 CST 2020
     */
    GatewayLog selectByPrimaryKey(@Param("reqId") String reqId, @Param("sequenceId") String sequenceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Mon Nov 30 21:09:52 CST 2020
     */
    List<GatewayLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gateway_log
     *
     * @mbg.generated Mon Nov 30 21:09:52 CST 2020
     */
    int updateByPrimaryKey(GatewayLog record);
}