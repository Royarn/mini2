package com.royarn.mini.service;

import com.royarn.mini.entity.TxData;

import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-06
 */
public interface TxDataService {

    /**
     * @param ids
     * @return
     */
    List<TxData> get(List<String> ids);

    /**
     *
     * @return
     */
    List<TxData> list();

    /**
     *
     * @param txData
     * @return
     */
    int insert(TxData txData);

    /**
     * @param txDatas
     * @return
     */
    List<TxData> batchInsert(List<TxData> txDatas);

    /**
     * @param txData
     * @return
     */
    TxData update(TxData txData);

    /**
     * @param ids
     * @return
     */
    int delete(List<String> ids);
}
