package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    private Category category;
}
