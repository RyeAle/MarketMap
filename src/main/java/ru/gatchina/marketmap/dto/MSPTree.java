package ru.gatchina.marketmap.dto;

import lombok.Data;

import java.util.List;

@Data
public class MSPTree {
    private Integer mapId;

    private List<Integer> requiredProducts;
}
