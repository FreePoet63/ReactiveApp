package razlivinsky.reactiveApp.controllers;

import org.springframework.web.bind.annotation.*;
import razlivinsky.reactiveApp.domain.Message;
import razlivinsky.reactiveApp.service.MessageService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class MainController {
    private final MessageService messageService;

    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "4") Long count
    ) {
        return messageService.list();
    }

    @PostMapping
    public Mono<Message> add(@RequestBody Message message) {
        return messageService.addOne(message);
    }
}
