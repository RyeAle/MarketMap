package ru.gatchina.marketmap.domain;

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
        @NamedEntityGraph(name = "category.all", includeAllAttributes = true)
})
public class Category {

    @Id
    @GeneratedValue
    @JsonView(Shop.Default.class)
    private Integer id;

    @JsonView(Shop.Default.class)
    private String name;

    @JsonView(Shop.Default.class)
    private String logoUrl;

    @ToString.Exclude
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;
}
