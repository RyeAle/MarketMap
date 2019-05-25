package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
