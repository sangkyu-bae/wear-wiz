package com.example.wearwizgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public StopWatch stopWatch;

    public GlobalFilter() {
        super(Config.class);
        stopWatch = new StopWatch("API Gateway");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            stopWatch.start();
            log.info("[글로벌 필터] REQUEST 요청 >>>> IP :{} ,URI : {}", request.getRemoteAddress().getAddress(),request.getURI());
            // Custom Post Filter. Suppose we can call error response handler based on error code.
            stopWatch.stop();
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("[글로벌 필터] RESPONSE 응답 >>>> IP : {}, URI : {}, 응답코드 : {} ---> 처리 시간 {}",
                        request.getRemoteAddress().getAddress(),
                        request.getURI(),
                        response.getStatusCode(),
                        stopWatch.getLastTaskTimeMillis()
                );
            }));
        };
    }

    public static class Config {

    }

}
