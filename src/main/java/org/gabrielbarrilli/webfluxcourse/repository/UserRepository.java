package org.gabrielbarrilli.webfluxcourse.repository;

import lombok.RequiredArgsConstructor;
import org.gabrielbarrilli.webfluxcourse.entity.User;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<User> save(final User user) {
        return mongoTemplate.save(user);
    }
}
