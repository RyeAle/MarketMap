package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
