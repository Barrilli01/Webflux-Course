package org.gabrielbarrilli.webfluxcourse.controller;


import jakarta.validation.Valid;
import org.gabrielbarrilli.webfluxcourse.entity.request.UserRequest;
import org.gabrielbarrilli.webfluxcourse.entity.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
@RestController
@RequestMapping("/api")

 */
public interface UserController {

    @GetMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> find(@PathVariable String id);

    @GetMapping
    ResponseEntity<Flux<UserResponse>> findAll();

    @PostMapping
    ResponseEntity<Mono<Void>> save(@Valid @RequestBody UserRequest request);

    @PatchMapping(value = "/{id}")
    ResponseEntity<Mono<UserResponse>> update(@PathVariable String id, @RequestBody UserRequest request);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Mono<Void>> delete(@PathVariable String id);
}
























