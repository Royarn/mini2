package com.royarn.mini.java8.template;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */

import java.util.function.Consumer;

/**
 * 模板模式  --抽象类在其中的作用为流程指定  差异化的细节由继承类提供，以降低代码冗余
 * 在算法框架中  --一般指定算法执行规则  具体算法细节在子类中实现
 */
public class OnlineBanking {

    public void processCustom(String id, Consumer<String> consumer) {
        System.out.println("check user whether exist ... ");
        consumer.accept(id);
    }
}
