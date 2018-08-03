package com.royarn.mini.kafka.producer;

import com.royarn.mini.entity.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/2 11:27
 */

@Component
public class Producer {

    private final KafkaTemplate<Object, MessageInfo> kafkaTemplate;
    private Logger logger = LoggerFactory.getLogger(Producer.class);

    Producer(KafkaTemplate<Object, MessageInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MessageInfo messageInfo) {
        kafkaTemplate.send("testTopic", messageInfo);
        logger.info("=============messageInfo============== " + messageInfo);
    }
}