package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Override
    @EntityGraph("category.all")
    Optional<Category> findById(Integer integer);
}
