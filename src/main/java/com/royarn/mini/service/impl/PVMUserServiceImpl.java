package com.royarn.mini.service.impl;

import com.google.gson.Gson;
import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Group;
import com.royarn.mini.entity.User;
import com.royarn.mini.service.PVMUserService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.PinYinUtil;
import com.royarn.mini.util.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-24
 */
@Service
public class PVMUserServiceImpl implements PVMUserService {

    @Resource
    private MongoConfig config;

    @Override
    public User selectOne(String id) {
        return config.mongoTemplate().findOne(new Query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public List<User> list(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public User addOne(String body) {
        User user = new Gson().fromJson(body, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setPinYin(PinYinUtil.getPinYinString(user.getName()).pinyin);
        user.setPinYinAd(PinYinUtil.spellFirst(user.getName()));
        Group group = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(user.getGroupId())), Group.class);
        if (null == group) { throw new BusinessException("分组不存在！"); }
        config.mongoTemplate().insert(user);
        return user;
    }

    @Override
    public User update(String id, String body) {
        User user = new Gson().fromJson(body, User.class);
        Group group = config.mongoTemplate().findOne(new Query(Criteria.where("id").is(user.getGroupId())), Group.class);
        if (null == group) { throw new BusinessException("分组不存在！"); }
        config.mongoTemplate().save(user);
        return user;
    }

    @Override
    public DeleteResult delete(List<String> ids) {
        return config.mongoTemplate().remove(new Query(Criteria.where("id").in(ids)), User.class);
    }

    @Override
    public List<User> getAllGroups() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<User> qryChildGroups(String parentId) {
        return null;
    }

    @Override
    public List<User> qryChildGroupsByPage(int currentPage, int pageSize, String parentId) {
        return null;
    }

    @Override
    public Long getChildGroupCount(String parentId) {
        return null;
    }

    @Override
    public List<User> qryUsersOfGroupByPage(int currentPage, int pageSize, String groupId, String regex) {

        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator( Criteria.where("name").regex(regex));
        } else {
            criteria.andOperator(Criteria.where("groupId").is(groupId));
        }
        List<User> users = config.mongoTemplate().find(new Query(criteria).limit(pageSize).skip(currentPage), User.class);
        return users;
    }

    @Override
    public Long qryUsersOfGroupCount(String groupId, String regex) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(regex)) {
            Pattern pattern = Pattern.compile("^.*"+regex+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator(Criteria.where("name").regex(pattern));
        }
        criteria.andOperator(Criteria.where("groupId").is(groupId));
        return config.mongoTemplate().count(new Query(criteria), User.class);
    }
}
