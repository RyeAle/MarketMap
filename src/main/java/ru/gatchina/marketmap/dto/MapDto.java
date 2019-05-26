package ru.gatchina.marketmap.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MapDto {

    private Integer shopId;

    private Integer width;

    private Integer height;

    private Set<BlockDto> blocks;

}
