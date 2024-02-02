package org.gabrielbarrilli.webfluxcourse.entity.response;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password) {
}
