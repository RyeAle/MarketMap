package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Map {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer width;

    private Integer height;

    private Integer floor;

    @ManyToOne
    private Shop shop;

    @OneToMany(mappedBy = "block")
    private Set<Block> blocks;
}
