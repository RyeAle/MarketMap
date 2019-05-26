package ru.gatchina.marketmap.dto;

import lombok.Data;

import java.util.List;

@Data
public class BlockDto {

    private Integer x;

    private Integer y;

    private String type;

    private Integer categoryId;

    private List<Integer> products;
}
