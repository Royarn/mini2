package com.royarn.mini.util;

import java.util.Collection;

public class Collectionutil {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !Collectionutil.isEmpty(collection);
    }
}
