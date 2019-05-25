package ru.gatchina.marketmap.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gatchina.marketmap.domain.Block;

public interface BlockRepository extends CrudRepository<Block, Integer> {
}
