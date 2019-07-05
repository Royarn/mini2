package com.royarn.mini.util;

import com.google.gson.Gson;

import java.util.Collection;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-05-23
 */
public class GsonUtils {

    public static String listoStr(Collection collection) {
        if (CollectionUtil.isEmpty(collection)) {
            return "";
        }
        return new Gson().toJson(collection);
    }
}
