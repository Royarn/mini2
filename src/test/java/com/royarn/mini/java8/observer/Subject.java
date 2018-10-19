package com.royarn.mini.java8.observer;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */

/**
 * 被观察者  --主题
 * 聚合所有观察者
 * 发布主题
 */
public interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers(String message);
}
