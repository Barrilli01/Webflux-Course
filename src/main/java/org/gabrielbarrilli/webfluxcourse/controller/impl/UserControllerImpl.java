package org.gabrielbarrilli.webfluxcourse.controller.impl;

import lombok.RequiredArgsConstructor;
import org.gabrielbarrilli.webfluxcourse.controller.UserController;
import org.gabrielbarrilli.webfluxcourse.entity.request.UserRequest;
import org.gabrielbarrilli.webfluxcourse.entity.response.UserResponse;
import org.gabrielbarrilli.webfluxcourse.mapper.UserMapper;
import org.gabrielbarrilli.webfluxcourse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return ResponseEntity.ok().body(
                userService.findAll()
                        .map(userMapper::toResponse)
        );
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(final String id) {
        return ResponseEntity.ok().body(
                userService.findById(id).map(userMapper::toResponse)
        );
    }

    @Override
    public ResponseEntity<Mono<Void>> save(final UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(
                userService.update(id, userRequest).map(userMapper::toResponse)
        );
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(@PathVariable String id) {
        return null;
    }
}
