package ru.gatchina.marketmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gatchina.marketmap.domain.Network;

public interface NetworkRepository extends JpaRepository<Network, Integer> {
}
