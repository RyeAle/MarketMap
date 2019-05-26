package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.gatchina.marketmap.domain.Map;

import java.util.Optional;

public interface MapRepository extends CrudRepository<Map, Integer> {

    @EntityGraph("map.all")
    Optional<Map> findWithAllById(Integer id);
}
