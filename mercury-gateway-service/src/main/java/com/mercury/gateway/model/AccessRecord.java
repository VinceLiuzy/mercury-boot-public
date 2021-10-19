package com.mercury.gateway.model;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/5/18 01:23
 **/
@Data
public class AccessRecord implements Serializable {
    private String path;
    private String scheme;
    private String method;
    private String targetUri;
    private String remoteAddress;
    private String body;
    private HttpHeaders headers;
    private MultiValueMap<String, String> formData;
}
