package com.example.wearwizgateway.filter;

import com.example.wearwizgateway.config.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private RedisTemplate<String,Object> redisTemplate;

    private JwtProvider jwtProvider;

    @Autowired
    public AuthFilter(RedisTemplate<String, Object> redisTemplate, JwtProvider jwtProvider) {
        super(AuthFilter.Config.class);
        this.redisTemplate = redisTemplate;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public GatewayFilter apply(AuthFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            List<String> authHeaders = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
            if(authHeaders==null){
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String jwt = authorizationHeader.replace("Bearer", "");

            if(!jwtProvider.validateJwtToken(jwt)){
                return onError(exchange,"no Valid Jwt" , HttpStatus.UNAUTHORIZED);
            }

            Claims claims = jwtProvider.getClaimsFromJwtToken(jwt);
            String userId = claims.getSubject();

            String value = (String) redisTemplate.opsForValue().get(userId);

            if(value == null){
                return onError(exchange,"black box Jwt",HttpStatus.UNAUTHORIZED);
            }

            exchange = exchange.mutate()
                    .request(builder -> builder.header("userId", userId))
                    .build();

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.error(err);
        return response.setComplete();
    }

    public static class Config {

    }
}
