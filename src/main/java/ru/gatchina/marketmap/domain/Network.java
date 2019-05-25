package ru.gatchina.marketmap.domain;

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
    private Integer id;

    private String name;

    private String urlPath;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "network", fetch = FetchType.LAZY)
    private Set<Shop> shops;
}
