package com.royarn.mini.util;

import com.royarn.mini.support.BusinessException;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-20
 */
public class PageUtils {

    public static int getOffset(String currentPage, String pageSize) {
        if (currentPage==null)
            throw new BusinessException("当前页数为空！");
        int offset = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(pageSize);
        if(offset<0)
            throw new BusinessException("分页数错误！");
        return offset;
    }

    public static List<String> getSearchList(String search){
        List<String> searchList=null;
        if(search!=null&&!StringUtils.isEmpty(search.trim())){
            String[] split = search.trim().split("\\s+");
            searchList= Arrays.asList(split);
        }
        return searchList;
    }
}
