package com.codewithzenas.store.repositories;

import com.codewithzenas.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}