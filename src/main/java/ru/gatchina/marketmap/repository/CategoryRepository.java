package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
