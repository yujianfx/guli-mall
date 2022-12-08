package cloud.stackexplode.gulimall.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder
//                .routes()
//                .route("auth", r -> r.path("/api/auth/**").filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/api(?<path>.*)", " $/{path}")).uri("lb://auth-service"))
//                .build();
//    }
}
