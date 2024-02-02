package org.gabrielbarrilli.webfluxcourse.entity.request;

public record UserRequest(
        String name,
        String email,
        String password) {
}
