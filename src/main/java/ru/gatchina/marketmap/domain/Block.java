package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Block {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer x;

    private Integer y;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Block block;
}
