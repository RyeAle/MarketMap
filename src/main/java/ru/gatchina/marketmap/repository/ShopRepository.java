package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @EntityGraph("shop.blocks")
    Optional<Shop> findWithBlocksById(Integer id);
}
