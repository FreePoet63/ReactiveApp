package razlivinsky.reactiveApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import razlivinsky.reactiveApp.handlers.GreetingHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        RequestPredicate route = RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(route, greetingHandler::hello)
                .andRoute(
                        RequestPredicates.GET("/"),
                        greetingHandler::index
                );
    }
}
