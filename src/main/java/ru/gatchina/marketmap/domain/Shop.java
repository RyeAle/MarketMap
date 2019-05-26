package ru.gatchina.marketmap.domain;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
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
    @JsonView(Default.class)
    private Integer id;

    @JsonView(Default.class)
    private Double longitude;

    @JsonView(Default.class)
    private Double latitude;

    @JsonView(Default.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH.mm")
    private Date openTime;

    @JsonView(Default.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH.mm")
    private Date closeTime;

    @ManyToOne
    @JsonManagedReference
    @JsonView(Default.class)
    private Network network;

    @ToString.Exclude
    @JsonManagedReference
    @JsonView(Default.class)
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

    public class Default{}
}
