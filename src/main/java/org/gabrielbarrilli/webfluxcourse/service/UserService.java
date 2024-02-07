package org.gabrielbarrilli.webfluxcourse.service;

import lombok.RequiredArgsConstructor;
import org.gabrielbarrilli.webfluxcourse.entity.User;
import org.gabrielbarrilli.webfluxcourse.entity.request.UserRequest;
import org.gabrielbarrilli.webfluxcourse.mapper.UserMapper;
import org.gabrielbarrilli.webfluxcourse.repository.UserRepository;
import org.gabrielbarrilli.webfluxcourse.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<User> save(final UserRequest request) {
        return userRepository.save(userMapper.toEntity(request));
    }

    public Mono<User> findById(final String id) {
        return userRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ObjectNotFoundException(
                                    format("Object not found. Id: %s, Type: %s" , id, User.class.getSimpleName())
                                )
                        )
                );
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> update(final String id, final UserRequest request) {
        return findById(id)
                .map(entity -> userMapper.toEntity(request, entity))
                .flatMap(userRepository::save);
    }
}
