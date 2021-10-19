package com.mercury.gateway.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/5/18 01:21
 **/
@Data
@Component
@ConfigurationProperties(prefix = "mercury.ignore")
public class IgnoreUrl {
    private List<String> urls;
}
