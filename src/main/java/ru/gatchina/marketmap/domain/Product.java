package ru.gatchina.marketmap.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    private Category category;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "products")
    private Set<Shop> shops;
}
