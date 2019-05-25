package ru.gatchina.marketmap.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Product> products;
}
