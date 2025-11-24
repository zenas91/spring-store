package com.codewithzenas.store.repositories;

import com.codewithzenas.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}