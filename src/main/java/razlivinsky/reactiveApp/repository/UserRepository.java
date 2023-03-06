package razlivinsky.reactiveApp.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import razlivinsky.reactiveApp.domain.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String name);
}
