package ru.gatchina.marketmap.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String logoUrl;
}
