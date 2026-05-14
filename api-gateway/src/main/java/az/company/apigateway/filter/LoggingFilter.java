package az.company.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String requestId = UUID.randomUUID().toString();

        ServerHttpRequest modifiedRequest = exchange.getRequest()
                .mutate()
                .header(REQUEST_ID_HEADER, requestId)
                .build();

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(modifiedRequest)
                .build();

        log.info("-> [{}] Method: {} | Path: {} | Headers: {}",
                requestId,
                modifiedRequest.getMethod(),
                modifiedRequest.getURI().getPath(),
                modifiedRequest.getHeaders()
        );

        return chain.filter(modifiedExchange).then(
                Mono.fromRunnable(() -> {
                    int statusCode = Objects.requireNonNull(modifiedExchange.getResponse()
                                    .getStatusCode())
                            .value();

                    log.info("<- [{}] Response Status {} | Path: {}",
                            requestId,
                            statusCode,
                            modifiedRequest.getURI().getPath()
                    );
                })
        );
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
