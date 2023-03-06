package razlivinsky.reactiveApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import razlivinsky.reactiveApp.domain.Message;
import razlivinsky.reactiveApp.repository.MessageRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Flux<Message> list() {
        return messageRepository.findAll();
    }

    public Mono<Message> addOne(Message message) {
        return messageRepository.save(message);
    }
}

