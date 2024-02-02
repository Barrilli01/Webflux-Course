package org.gabrielbarrilli.webfluxcourse.controller;


import org.gabrielbarrilli.webfluxcourse.entity.request.UserRequest;
import org.gabrielbarrilli.webfluxcourse.entity.response.UserResponse;
import org.gabrielbarrilli.webfluxcourse.service.UserService;
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
    ResponseEntity<Mono<UserResponse>> find(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Flux<UserResponse>> findAll();

    @PostMapping
    ResponseEntity<Mono<Void>> save(@RequestBody UserRequest request){
        return usuarioService.save(request);
    }

//    @PatchMapping(value = "/{id}")
//    ResponseEntity<Mono<UserResponse>> update(@PathVariable Long id, RequestBody UserRequest request);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Mono<Void>> delete(@PathVariable Long id);
}
























