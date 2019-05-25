package ru.gatchina.marketmap.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gatchina.marketmap.domain.Map;

public interface MapRepository extends CrudRepository<Map, Integer> {
}
