package ru.gatchina.marketmap.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Integer id;

    private String name;

    private String logoUrl;

    @ToString.Exclude
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;
}
