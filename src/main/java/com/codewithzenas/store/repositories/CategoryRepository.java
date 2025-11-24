package com.codewithzenas.store.repositories;

import com.codewithzenas.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}