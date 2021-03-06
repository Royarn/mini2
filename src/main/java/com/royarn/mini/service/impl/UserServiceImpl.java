package com.royarn.mini.service.impl;

import com.alibaba.fastjson.JSON;
import com.royarn.mini.config.ResultCode;
import com.royarn.mini.dao.LocalUserMapper;
import com.royarn.mini.entity.LocalUser;
import com.royarn.mini.entity.LocalUserExample;
import com.royarn.mini.entity.MsgInfo;
import com.royarn.mini.service.UserService;
import com.royarn.mini.support.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 16:54
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private LocalUserMapper mapper;

    @Resource
    private MongoTemplate template;

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Resource
    private PlatformTransactionManager txManager;

    public List<LocalUser> get(List<String> ids) {
        LocalUserExample example = new LocalUserExample();
        LocalUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.selectByExample(example);
    }

    @Override
    public List<LocalUser> list() {
        return mapper.selectByExample(null);
    }

    @Override
    public int insert(LocalUser user) {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        int result;
        try {
            //insert users
            if (StringUtils.isBlank(user.getName())) { throw new BusinessException("用户名称不能为空！");}
            if (StringUtils.isBlank(user.getPassword())) { throw new BusinessException("密码不能为空！");}
            if (checkIfExist(user.getName())) { throw new BusinessException("用户名已存在！");}
            user.setId(UUID.randomUUID().toString());
            result = mapper.insert(user);
            if (result == 0 || result != 0) {
                //insert shops
                Map<String, String> map = new HashMap<>();
                map.put("id", user.getId());
                map.put("code", "200");
                map.put("info", user.getRemark());
                kafkaTemplate.send("test", JSON.toJSONString(map));
            }
        } catch (Exception e) {
            txManager.rollback(status);
        } finally {
            txManager.commit(status);
        }

        return 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public List<LocalUser> batchInsert(List<LocalUser> users) {
        //先找无效的数据
        List<LocalUser> realUsers = new ArrayList<>();
        List<LocalUser> illeUsers = new ArrayList<>();
        List<String> userNames = new ArrayList<>();
        Map<String, LocalUser> userMap = new HashMap<>();
        for (LocalUser user : users) {
            if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())) {
                illeUsers.add(user);
            } else {
                user.setId(UUID.randomUUID().toString());
                userMap.put(user.getName(), user);
                userNames.add(user.getName());
            }
        }
        LocalUserExample example = new LocalUserExample();
        LocalUserExample.Criteria criteria = example.createCriteria();
        criteria.andNameIn(userNames);
        List<LocalUser> existUsers = mapper.selectByExample(example);
        Map<String, LocalUser> existMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(existUsers)) {
            for (LocalUser user : existUsers) {
                existMap.put(user.getName(), user);
            }
        }
        //过滤已经存在的数据
        for (String name : userMap.keySet()) {
            if (!existMap.containsKey(name)) {
                realUsers.add(userMap.get(name));
            }
        }
        //插入数据
        if (CollectionUtils.isEmpty(realUsers))
            return illeUsers;
        mapper.batchInsert(realUsers);
        return illeUsers;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public LocalUser update(LocalUser user) {
        //更新前校验
        LocalUserExample example = new LocalUserExample();
        LocalUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(user.getId());
        criteria.andNameEqualTo(user.getName());
        List<LocalUser> users = mapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users))
            throw new BusinessException("用户名已存在");
        mapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public int delete(List<String> ids) {
        LocalUserExample example = new LocalUserExample();
        LocalUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return mapper.deleteByExample(example);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = BusinessException.class)
    public String insertDTS() {
        //mongo transaction
        MsgInfo info = new MsgInfo();
        info.setCode(ResultCode.suc.getMessage());
        info.setInfo("DTS has committed");
        template.insert(info);
        //local transaction
        LocalUser user = new LocalUser();
        user.setId("96446dec-55e7-465b-8fdc-16b08f08b509");
        user.setName("admin");
        user.setPassword("admin444");
        mapper.insert(user);
        return ResultCode.suc.getMessage();
    }

    private boolean checkIfExist(String name) {
        LocalUserExample example = new LocalUserExample();
        LocalUserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<LocalUser> users = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users))
            return false;
        return true;
    }
}
