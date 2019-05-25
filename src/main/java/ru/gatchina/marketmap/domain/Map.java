package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.*;

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
}
