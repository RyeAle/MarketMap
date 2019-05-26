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
public class Network {

    @Id
    @GeneratedValue
    @JsonView(Shop.Default.class)
    private Integer id;

    @JsonView(Shop.Default.class)
    private String name;

    @JsonView(Shop.Default.class)
    private String logoUrl;

    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "network", fetch = FetchType.LAZY)
    private Set<Shop> shops;
}
