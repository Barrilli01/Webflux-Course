package org.gabrielbarrilli.webfluxcourse.repository;

import org.gabrielbarrilli.webfluxcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
