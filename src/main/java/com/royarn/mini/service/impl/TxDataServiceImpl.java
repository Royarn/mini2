package com.royarn.mini.service.impl;

import com.royarn.mini.dao.TxDataMapper;
import com.royarn.mini.entity.TxData;
import com.royarn.mini.entity.TxDataExample;
import com.royarn.mini.service.TxDataService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.support.TxDataType;
import com.royarn.mini.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-06
 */
@Service
public class TxDataServiceImpl implements TxDataService {

    @Resource
    private TxDataMapper mapper;

    @Override
    public List<TxData> get(List<String> ids) {
        TxDataExample example = new TxDataExample();
        TxDataExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<TxData> list() {
        return mapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int insert(TxData txData) {
        txData.setId(UUID.randomUUID().toString());
        return mapper.insert(txData);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public List<TxData> batchInsert(List<TxData> txDatas) {
        txDatas = txDatas.stream()
                .filter(txData -> StringUtils.isNotEmpty(txData.getData()))
                .map(txData -> {
                    txData.setId(UUID.randomUUID().toString());
                    txData.setStatus(TxDataType.NEW.value());
                    return txData;
                })
                .collect(Collectors.toList());
        mapper.batchInsert(txDatas);
        return txDatas;
    }

    @Override
    public TxData update(TxData txData) {
        return null;
    }

    @Override
    public int delete(List<String> ids) {
        TxDataExample example = new TxDataExample();
        TxDataExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }
}