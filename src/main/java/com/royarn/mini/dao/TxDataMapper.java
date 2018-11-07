package com.royarn.mini.dao;

import com.royarn.mini.entity.TxData;
import com.royarn.mini.entity.TxDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TxDataMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int countByExample(TxDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int deleteByExample(TxDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int insert(TxData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int insertSelective(TxData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    List<TxData> selectByExample(TxDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    TxData selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") TxData record, @Param("example") TxDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int updateByExample(@Param("record") TxData record, @Param("example") TxDataExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int updateByPrimaryKeySelective(TxData record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tx_data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    int updateByPrimaryKey(TxData record);

    int batchInsert(@Param("txData") List<TxData> txData);
}