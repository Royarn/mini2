package com.royarn.mini.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dev_access_gb")
public class GbAccess {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dev_access_gb.id
     *
     * @mbggenerated
     */
    @Id
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dev_access_gb.gb_id
     *
     * @mbggenerated
     */
    private String gbId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dev_access_gb.pass
     *
     * @mbggenerated
     */
    private String pass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dev_access_gb.id
     *
     * @return the value of dev_access_gb.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dev_access_gb.id
     *
     * @param id the value for dev_access_gb.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dev_access_gb.gb_id
     *
     * @return the value of dev_access_gb.gb_id
     *
     * @mbggenerated
     */
    public String getGbId() {
        return gbId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dev_access_gb.gb_id
     *
     * @param gbId the value for dev_access_gb.gb_id
     *
     * @mbggenerated
     */
    public void setGbId(String gbId) {
        this.gbId = gbId == null ? null : gbId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dev_access_gb.pass
     *
     * @return the value of dev_access_gb.pass
     *
     * @mbggenerated
     */
    public String getPass() {
        return pass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dev_access_gb.pass
     *
     * @param pass the value for dev_access_gb.pass
     *
     * @mbggenerated
     */
    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }
}