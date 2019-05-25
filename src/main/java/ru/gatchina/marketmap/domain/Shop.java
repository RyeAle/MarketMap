package ru.gatchina.marketmap.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "shop.blocks", attributeNodes = {
                @NamedAttributeNode(value = "maps", subgraph = "maps.blocks")
        }, subgraphs = {
                @NamedSubgraph(name = "maps.blocks", attributeNodes = {
                        @NamedAttributeNode("blocks")
                })
        })
})
public class Shop {
    @Id
    @GeneratedValue
    private Integer id;

    private Double longitude;

    private Double latitude;

    @ToString.Exclude
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Map> maps;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "shop_product",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products;

    @ManyToOne
    @JsonManagedReference
    private Network network;
}
