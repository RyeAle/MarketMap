package ru.gatchina.marketmap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonBackReference
    private Category category;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "products")
    private Set<Shop> shops;

    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Block> blocks;
}
