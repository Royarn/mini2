//package com.royarn.mini.config;
//
//import com.royarn.mini.entity.MessageInfo;
//import com.royarn.mini.kafka.producer.Producer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author lizq
// * @Description: ${todo}
// * @date 2018/8/2 13:37
// */
//@Component
//public class SendExecutor {
//
//    @Resource
//    private Producer producer;
//
//    @PostConstruct
//    public void sendExecutor() {
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    producer.send(new MessageInfo(1, "hello royarn"));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//}
