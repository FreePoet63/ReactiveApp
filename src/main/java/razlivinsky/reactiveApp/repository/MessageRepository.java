package razlivinsky.reactiveApp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import razlivinsky.reactiveApp.domain.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
