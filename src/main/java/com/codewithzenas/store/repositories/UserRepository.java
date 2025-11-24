package com.codewithzenas.store.repositories;

import com.codewithzenas.store.dtos.UserDto;
import com.codewithzenas.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
