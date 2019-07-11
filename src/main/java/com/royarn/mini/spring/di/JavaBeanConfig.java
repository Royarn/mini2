package com.royarn.mini.spring.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-10
 */
@Configuration
@ComponentScan("com.royarn.mini.spring.di")
public class JavaBeanConfig {

    //test
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaBeanConfig.class);
        CoupleBean bean = (CoupleBean) context.getBean("coupleBean");
        bean.doCall();
    }

    @Bean
    public JavaBean bean() {
        return new JavaBean();
    }

    @Bean
    public CoupleBean coupleBean() {
        CoupleBean coupleBean = new CoupleBean();
        coupleBean.setBean(bean());
        return coupleBean;
    }

    @Bean
    public CoupleBean coupleBean(JavaBean bean) {
        CoupleBean coupleBean = new CoupleBean();
        coupleBean.setBean(bean);
        return coupleBean;
    }
}

//Annotation injection
@Component
class Pojo {
    public void print() {
        System.out.println("This is a simple plain object");
    }
}

@Service
class MultiBean {
    @Resource
    private Pojo pojo;

    public void doCall() {
        pojo.print();
    }
}

//Java config
class JavaBean {
    public void print() {
        System.out.println("This is a simple plain object by java config");
    }
}

class CoupleBean {
    private JavaBean bean;

    public void setBean(JavaBean bean) {
        this.bean = bean;
    }

    public void doCall() {
        bean.print();
    }
}