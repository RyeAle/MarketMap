package ru.gatchina.marketmap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Block {

    @Id
    @GeneratedValue
    @JsonView(Shop.Default.class)
    private Integer id;

    @JsonView(Shop.Default.class)
    private Integer x;

    @JsonView(Shop.Default.class)
    private Integer y;

    @JsonView(Shop.Default.class)
    private BlockType blockType;

    @ManyToOne
    @JsonView(Shop.Default.class)
    private Category category;

    @ManyToOne
    @JsonBackReference
    private Map map;

    public Block(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "block_product",
            joinColumns = @JoinColumn(name = "block_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products;
}
