package com.royarn.mini.support;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-06
 */
public enum  TxDataType {
    NEW(0), PUBLISHED(1);

    private int status;
    TxDataType(int status) {
        this.status = status;
    }

    public byte value() {
        return new Integer(status).byteValue();
    }
}