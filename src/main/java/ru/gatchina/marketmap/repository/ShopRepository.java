package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
}
