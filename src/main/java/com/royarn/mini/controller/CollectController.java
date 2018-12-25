//package com.royarn.mini.controller;
//
//import com.royarn.mini.config.Result;
//import com.royarn.mini.util.StringUtils;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * Description:
// *
// * @author dell
// * @date 2018-11-05
// */
//@RestController
//@RequestMapping("/kafka")
//public class CollectController {
//
//    @Resource
//    private KafkaTemplate template;
//
//    @ApiOperation("kafka发送消息")
//    @PostMapping("/send")
//    public Result sendMsg(@RequestBody String msg) {
//        try {
//            if (StringUtils.isEmpty(msg)) {
//                return Result.error("发送消息不能为空！");
//            }
//            return Result.ok().property("data", template.send("test", msg));
//        } catch (Exception e) {
//            return Result.error("发送消息失败！");
//        }
//    }
//}
