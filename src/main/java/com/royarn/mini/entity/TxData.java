package com.royarn.mini.entity;

public class TxData {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tx_data.id
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tx_data.status
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tx_data.data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    private String data;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tx_data.id
     *
     * @return the value of tx_data.id
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tx_data.id
     *
     * @param id the value for tx_data.id
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tx_data.status
     *
     * @return the value of tx_data.status
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tx_data.status
     *
     * @param status the value for tx_data.status
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tx_data.data
     *
     * @return the value of tx_data.data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public String getData() {
        return data;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tx_data.data
     *
     * @param data the value for tx_data.data
     *
     * @mbggenerated Tue Nov 06 16:02:34 CST 2018
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}