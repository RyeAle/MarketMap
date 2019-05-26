package ru.gatchina.marketmap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = "map.all", includeAllAttributes = true),
        @NamedEntityGraph(name = "map.blocks", attributeNodes = {
                @NamedAttributeNode(value = "blocks", subgraph = "block.category")
        })
})
public class Map {
    @Id
    @GeneratedValue
    @JsonView(Shop.Default.class)
    private Integer id;

    @JsonView(Shop.Default.class)
    private Integer width;

    @JsonView(Shop.Default.class)
    private Integer height;

    @JsonView(Shop.Default.class)
    private Integer floor;

    @ManyToOne
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Shop shop;

    @ToString.Exclude
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @JsonView(Shop.Default.class)
    @OneToMany(mappedBy = "map", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Block> blocks;
}
