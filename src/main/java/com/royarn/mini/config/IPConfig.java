package com.royarn.mini.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 10:21
 */
@Configuration
@ConfigurationProperties(prefix = "hostconf")
public class IPConfig {

    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
