package org.gabrielbarrilli.webfluxcourse.controller;

import com.mongodb.reactivestreams.client.MongoClient;
import org.gabrielbarrilli.webfluxcourse.entity.User;
import org.gabrielbarrilli.webfluxcourse.entity.request.UserRequest;
import org.gabrielbarrilli.webfluxcourse.entity.response.UserResponse;
import org.gabrielbarrilli.webfluxcourse.mapper.UserMapper;
import org.gabrielbarrilli.webfluxcourse.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerImplTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private MongoClient mongoClient;

    final UserRequest validRequest = new UserRequest("Gabriel Barrilli" , "gabriel@gmail.com" , "123");
    final UserRequest invalidRequest = new UserRequest(" Gabriel Barrilli" , "gabrielgmail.com" , "12");

    @Test
    void findAll() {
    }

    @Test
    @DisplayName("Test find by id with success")
    void findById() {
        final var id = "123456";
        final var userResponse = new UserResponse(id ,"Gabriel Barrilli" , "gabriel@gmail.com" , "123");

        when(userService.findById(anyString())).thenReturn(Mono.just(User.builder().build()));
        when(userMapper.toResponse(any(User.class))).thenReturn(userResponse);

        webTestClient.get().uri("/users/" + id)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(id)
                .jsonPath("$.name").isEqualTo("Gabriel Barrilli")
                .jsonPath("$.email").isEqualTo("gabriel@gmail.com")
                .jsonPath("$.password").isEqualTo("123");
    }

    @Test
    @DisplayName("Test endpoint save with success")
    void testSaveWithSuccess() {

        when(userService.save(any(UserRequest.class))).thenReturn(Mono.just(User.builder().build()));

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(validRequest))
                .exchange()
                .expectStatus().isCreated();

        verify(userService).save(any(UserRequest.class));
    }

    @Test
    @DisplayName("Test endpoint save with bad request")
    void testSaveWithBadRequest() {

        webTestClient.post().uri("/users")
                .contentType(APPLICATION_JSON)
                .body(fromValue(invalidRequest))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.path").isEqualTo("/users")
                .jsonPath("$.status").isEqualTo(BAD_REQUEST.value())
                .jsonPath("$.error").isEqualTo("Validation error")
                .jsonPath("$.message").isEqualTo("Error on validation attributes")

                .jsonPath("$.errors[2].fieldName").isEqualTo("name")
                .jsonPath("$.errors[2].message").isEqualTo("field cannot have blank spaces at the beginning or at end")

                .jsonPath("$.errors[1].fieldName").isEqualTo("email")
                .jsonPath("$.errors[1].message").isEqualTo("invalid email")

                .jsonPath("$.errors[0].fieldName").isEqualTo("password")
                .jsonPath("$.errors[0].message").isEqualTo("must be between 3 and 20 characters");

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}