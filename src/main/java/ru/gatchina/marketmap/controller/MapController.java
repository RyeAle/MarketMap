package ru.gatchina.marketmap.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gatchina.marketmap.domain.*;
import ru.gatchina.marketmap.dto.BlockDto;
import ru.gatchina.marketmap.dto.MapDto;
import ru.gatchina.marketmap.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class MapController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/block/{id}")
    public Set<Product> getBlock(@PathVariable int id) {
        Optional<Block> block = blockRepository.findById(id);
        return block.map(Block::getProducts).orElse(null);
    }

    @GetMapping("/shop/{id}")
    @JsonView(Shop.Default.class)
    public Shop getShopInfo(@PathVariable int id, @RequestParam(name = "floor", defaultValue = "1") Integer f) {
        Optional<Shop> shop = shopRepository.findWithBlocksById(id);
        if (shop.isPresent() && f > 0) {
            Shop sh = shop.get();
            sh.getMaps().removeIf(s -> !s.getFloor().equals(f));
            sh.getMaps().forEach(m -> m.getBlocks().forEach(b -> b.setProducts(null)));
            return sh;
        }
        return null;
    }

    @GetMapping("/shop")
    public List<Shop> getShops() {
        List<Shop> shops = shopRepository.findAll();
        shops.forEach(l -> l.setMaps(null));
        return shops;
    }

    @Transactional
    @PostMapping(path = "/map", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMap(@RequestBody MapDto mapDto) {
        Shop shop = shopRepository.findById(mapDto.getShopId()).get();

        Map map = new Map();
        map.setHeight(mapDto.getHeight());
        map.setWidth(mapDto.getWidth());
        map.setFloor(1);
        map.setShop(shop);

        HashSet<Block> blocks = new HashSet<>();
        for (BlockDto bl : mapDto.getBlocks()) {
            Category category = categoryRepository.findById(bl.getCategoryId()).get();
            Block block = new Block(bl.getX(), bl.getY());

            block.setBlockType(BlockType.valueOf(bl.getType()));
            block.setCategory(category);
            block.setMap(map);

            HashSet<Product> products = new HashSet<>();
            for (Integer productId : bl.getProducts()) {
                Product pr = productRepository.findById(productId).get();
                products.add(pr);
            }
            block.setProducts(products);
            blocks.add(block);
        }
        Iterable<Block> newBlocks = blockRepository.saveAll(blocks);
        HashSet<Block> blockSet = new HashSet<>();
        newBlocks.forEach(blockSet::add);
        map.setBlocks(blockSet);

        Map savedMap = mapRepository.save(map);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMap);
    }

    @GetMapping("/map/{id}")
    public Map getMap(@PathVariable Integer id) {
        return mapRepository.findWithAllById(id).orElse(null);
    }
}
