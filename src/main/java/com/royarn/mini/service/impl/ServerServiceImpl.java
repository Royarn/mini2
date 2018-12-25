package com.royarn.mini.service.impl;

import com.google.gson.*;
import com.mongodb.client.result.DeleteResult;
import com.royarn.mini.config.MongoConfig;
import com.royarn.mini.entity.Server;
import com.royarn.mini.service.ServerService;
import com.royarn.mini.support.BusinessException;
import com.royarn.mini.util.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
@Service
public class ServerServiceImpl implements ServerService {

    @Resource
    private MongoConfig config;

    @Override
    public Server selectOne(String serverId) {
        MongoTemplate template = config.mongoTemplate();
        if (serverId == null) {
            throw new RuntimeException("设备ID不能为空！");
        }
        //Query
        return template.findOne(new Query(Criteria.where("id").
                is(serverId)), Server.class);
    }

    @Override
    public List<Server> list(int pageSize, int pageNum) {
        return config.mongoTemplate()
                .find(new Query().limit(pageSize).skip(Long.valueOf(pageNum)), Server.class);
    }

    @Override
    public void addOne(Server server) {
        MongoTemplate template = config.mongoTemplate();
        server.setId(UUID.randomUUID().toString());
        template.insert(server);
    }

    @Override
    public void update(Server server) {
        if (StringUtils.isEmpty(server.getId())) {
            throw new BusinessException("服务ID不能为空！");
        }
        config.mongoTemplate().save(server);
    }

    @Override
    public DeleteResult delete(String id) {
        return config.mongoTemplate().remove(id);
    }

    @Override
    public List<Server> batch(String body) {
        MongoTemplate template = config.mongoTemplate();
        JsonArray serverList = new JsonParser().parse(body).getAsJsonArray();
        List<Server> servers = new ArrayList<>();
       for (int i=0;i<serverList.size();i++) {
           JsonObject jsonObject = serverList.get(i).getAsJsonObject();
           String type = jsonObject.get("type").getAsString();
           Server server = null;
           if ("pau".equals(type)) {
               String pauComponentList = jsonObject.get("pauComponentList").toString();
               jsonObject.remove("pauComponentList");
               server = new Gson().fromJson(serverList.get(i), Server.class);
               server.setPauComponentList(pauComponentList);
           }
           template.insert(server);
           servers.add(server);
       }
        return servers;
    }

    @Override
    public List<Server> qryServers(Integer offset, Integer pageSize, String search, List<String> types) {
        return config.mongoTemplate()
                .find(new Query().limit(pageSize).skip(Long.valueOf(offset)), Server.class);
    }

    @Override
    public Long count(String search, List<String> types) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotEmpty(search)) {
            Pattern pattern = Pattern.compile("^.*"+search+".*$", Pattern.CASE_INSENSITIVE);
            criteria.andOperator(Criteria.where("host").regex(pattern),
                    Criteria.where("port").regex(pattern),
                    Criteria.where("type").regex(pattern));
        }
//        if (null != types && types.size() > 0) {
//            criteria.andOperator(Criteria.where("type").in(types));
//        }
        List<Server> serverList = config.mongoTemplate().find(new Query(criteria), Server.class);
        return config.mongoTemplate().count(new Query(criteria), Server.class);
    }
}
