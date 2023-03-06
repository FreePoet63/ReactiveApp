package razlivinsky.reactiveApp.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import razlivinsky.reactiveApp.domain.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class GreetingHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(4L);

        Flux<Message> data = Flux
                .just(
                        "Hello, mother",
                        "You the best",
                        "First Love",
                        "Second love",
                        "Third Love"
                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        String user = request.queryParam("user")
                .orElse("Nobody");

        return ServerResponse
                .ok()
                .render("index", Collections.singletonMap("user", user));

    }
}
