package com.royarn.mini.java8.observer;

import com.royarn.mini.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class Tweet implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.receiveMsg(message));
    }

    public static void main(String[] args) {
        Tweet tweet = new Tweet();
        //lambda表达式构建不同的观察者
        tweet.registerObserver(message -> {
         if (StringUtils.isNotEmpty(message) && message.contains("money")) {
             System.out.println("this message will be included by NYTimes");
         }
        });

        tweet.registerObserver(message -> {
            if (StringUtils.isNotEmpty(message) && message.contains("queen")) {
                System.out.println("this message will be included by Guardian");
            }
        });

        tweet.registerObserver(message -> {
            if (StringUtils.isNotEmpty(message) && message.contains("wine")) {
                System.out.println("this message will be included by LeMonde");
            }
        });
        //发布消息
        tweet.notifyObservers("money");
        tweet.notifyObservers("queen");
        tweet.notifyObservers("wine");
    }
}
