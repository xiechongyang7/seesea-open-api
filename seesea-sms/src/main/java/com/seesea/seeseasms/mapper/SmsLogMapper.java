package com.seesea.seeseasms.mapper;

import com.seesea.seeseasms.entity.SmsLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_log
     *
     * @mbg.generated Tue Nov 24 20:16:45 CST 2020
     */
    int deleteByPrimaryKey(@Param("smsId") String smsId, @Param("reqId") String reqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_log
     *
     * @mbg.generated Tue Nov 24 20:16:45 CST 2020
     */
    int insert(SmsLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_log
     *
     * @mbg.generated Tue Nov 24 20:16:45 CST 2020
     */
    SmsLog selectByPrimaryKey(@Param("smsId") String smsId, @Param("reqId") String reqId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_log
     *
     * @mbg.generated Tue Nov 24 20:16:45 CST 2020
     */
    List<SmsLog> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_log
     *
     * @mbg.generated Tue Nov 24 20:16:45 CST 2020
     */
    int updateByPrimaryKey(SmsLog record);
}