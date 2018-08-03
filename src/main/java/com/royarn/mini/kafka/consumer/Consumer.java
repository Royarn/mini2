package com.royarn.mini.kafka.consumer;

import com.royarn.mini.entity.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/2 11:28
 */

@Component
public class Consumer {

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "oplog")
    public void processMessage(MessageInfo messageInfo) {
        logger.info("==========receive messageInfo============== " + messageInfo);
    }
}