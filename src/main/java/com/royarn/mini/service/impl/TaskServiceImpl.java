package com.royarn.mini.service.impl;

import com.royarn.mini.dao.MenuMapper;
import com.royarn.mini.entity.Menu;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author dell
 * @date 2019-01-02
 */
@Service
public class TaskServiceImpl {

    @Resource
    private MenuMapper mapper;

    private ExecutorService executor = Executors.newCachedThreadPool();

    //@Scheduled(fixedRate = 3000)
    public void list() {
        List<Menu> menuList = mapper.selectByExample(null);
        menuList.stream()
                .forEach(menu -> System.out.println(menu.getName()));
    }
}
