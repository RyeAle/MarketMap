package ru.gatchina.marketmap.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gatchina.marketmap.domain.Block;
import ru.gatchina.marketmap.domain.BlockType;
import ru.gatchina.marketmap.domain.Map;
import ru.gatchina.marketmap.domain.Product;
import ru.gatchina.marketmap.dto.MSPTree;
import ru.gatchina.marketmap.dto.PointDto;
import ru.gatchina.marketmap.repository.MapRepository;
import ru.gatchina.marketmap.repository.ProductRepository;

import java.util.*;

@RestController
public class PathController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapRepository mapRepository;

    @PostMapping("/path")
    public List<PointDto> getPath(@RequestBody MSPTree tree) {
        Map map = mapRepository.findWithAllById(tree.getMapId()).get();
        List<Product> products = productRepository.findAllById(tree.getRequiredProducts());
        Set<Block> blocks = map.getBlocks();
        Block startBlock = blocks.stream().filter(b -> b.getBlockType().equals(BlockType.ENTRY)).
                findFirst().get();
        Block endBlock = blocks.stream().filter(b -> b.getBlockType().equals(BlockType.CASH)).
                findFirst().get();

        for (Product product : products) {
            Set<Block> productBlocks = product.getBlocks();

        }

//        Wrapper[][] matrix = new Wrapper[map.getHeight()][map.getWidth()];
//        int startX = 0, startY = 0;
//        int cashX = 0, cashY = 0;
//        for (Block bl : blocks) {
//            if (bl.getBlockType().equals(BlockType.ENTRY)) {
//                startX = bl.getX();
//                startY = bl.getY();
//            } else if (bl.getBlockType().equals(BlockType.CASH)) {
//                cashX = bl.getX();
//                cashY = bl.getY();
//            }
//            matrix[bl.getY()][bl.getX()] = new Wrapper(false, bl, null);
//        }
//
//        matrix[startY][startX - 1].block.setBlockType(BlockType.SHELF);
//        Queue<Wrapper> queue = new ArrayDeque<>();
//        queue.add(matrix[startY][startX]);
//        while (products.size() != 0 || !queue.isEmpty()) {
//            Wrapper wrapper = queue.poll();
//            Block block = wrapper.block;
//            wrapper.isVisited = true;
//
//            if (block.getX() != null && block.getY() != null) {
//                if (block.getX() > 0 && matrix[block.getY()][block.getX() - 1] != null && !matrix[block.getY()][block.getX() - 1].block.
//                        getBlockType().equals(BlockType.SHELF) && !matrix[block.getY()][block.getX() - 1].isVisited){
//                    matrix[block.getY()][block.getX() - 1].prevBlock = wrapper;
//                    queue.add(matrix[block.getY()][block.getX() - 1]);
//                }
//
//                if (matrix[block.getY() + 1][block.getX()] != null && !matrix[block.getY() + 1][block.getX()].block.getBlockType().equals(BlockType.SHELF)
//                        && !matrix[block.getY() + 1][block.getX()].isVisited){
//                    matrix[block.getY() + 1][block.getX()].prevBlock = wrapper;
//                    queue.add(matrix[block.getY() + 1][block.getX()]);
//                }
//
//                if (matrix[block.getY()][block.getX() + 1] != null && !matrix[block.getY()][block.getX() + 1].block.
//                        getBlockType().equals(BlockType.SHELF) && !matrix[block.getY()][block.getX() + 1].isVisited){
//                    matrix[block.getY()][block.getX()+ 1].prevBlock = wrapper;
//                    queue.add(matrix[block.getY()][block.getX() + 1]);
//                }
//
//                if (block.getY() > 0 && matrix[block.getY() - 1][block.getX()] != null &&  !matrix[block.getY() - 1][block.getX()].block.
//                        getBlockType().equals(BlockType.SHELF) && !matrix[block.getY() - 1][block.getX()].isVisited) {
//                    matrix[block.getY()] [block.getX()].prevBlock = wrapper;
//                    queue.add(matrix[block.getY() - 1][block.getX()]);
//                }
//            }
//
//            products.removeIf(p -> block.getProducts().stream().
//                    anyMatch(pr -> pr.getId().equals(p.getId())));
//        }
//
//        Wrapper dest = matrix[cashY][cashX];
//        List<PointDto> points = new ArrayList<>();
//        while (dest != null) {
//            points.add(new PointDto(dest.block.getX(), dest.block.getY()));
//            dest = dest.prevBlock;
//        }
//        return points;
    }

    @AllArgsConstructor
    public class Wrapper {
        boolean isVisited;
        Block block;
        Wrapper prevBlock;
    }

}
