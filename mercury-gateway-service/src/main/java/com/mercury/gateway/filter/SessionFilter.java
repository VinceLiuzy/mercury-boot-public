package com.mercury.gateway.filter;

import com.mercury.crud.constant.GlobalConst;
import com.mercury.crud.dto.ApiResult;
import com.mercury.crud.exception.ExceptionEnum;
import com.mercury.gateway.model.IgnoreUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/5/18 01:20
 **/
@Component
@Slf4j
public class SessionFilter implements GlobalFilter, Ordered {
    @Autowired
    private IgnoreUrl ignoreUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpRequest httpRequest = exchange.getRequest();
        String url = httpRequest.getURI().getPath();

        if (!isInclude(url)) {
            return exchange.getSession().flatMap(webSession -> {
                log.info("websession: {}", webSession.getId());

                // session是否存在
                if (webSession.getAttributes().get(GlobalConst.CURRENT_USER) != null) {
                    return chain.filter(exchange);
                } else {
                    // session不存在 重新登陆
                    return getMono(exchange);
                }
            }).onErrorResume(e -> {
                // 获取session失败
                log.error(e.getMessage(), e);

                return getMono(exchange);
            });
        }

        return chain.filter(exchange);
    }

    private Mono<? extends Void> getMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] data = ApiResult.error(ExceptionEnum.UNAUTHORIZED).toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(data);

        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        Optional op = ignoreUrl.getUrls().stream()
                .filter(item -> Pattern.compile(item).matcher(url).matches())
                .findFirst();

        return op.isPresent();
    }
}
