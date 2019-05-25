package ru.gatchina.marketmap.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue
    private Integer id;

    private Double longitude;

    private Double latitude;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private Set<Map> maps;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "shop_product",
            joinColumns = @JoinColumn(name = "shops"),
            inverseJoinColumns = @JoinColumn(name = "products")
    )
    private Set<Product> products;

    @ManyToOne
    private Network network;
}
