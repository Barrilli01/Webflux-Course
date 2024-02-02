package org.gabrielbarrilli.webfluxcourse.entity.response;

public record UserResponse(
        String id,
        String name,
        String email,
        String password) {
}
