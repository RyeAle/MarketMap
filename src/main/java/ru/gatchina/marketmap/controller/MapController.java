package ru.gatchina.marketmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gatchina.marketmap.domain.Block;
import ru.gatchina.marketmap.domain.Product;
import ru.gatchina.marketmap.domain.Shop;
import ru.gatchina.marketmap.repository.BlockRepository;
import ru.gatchina.marketmap.repository.ShopRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class MapController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private BlockRepository blockRepository;

    @GetMapping("/block/{id}")
    public Set<Product> getBlock(@PathVariable int id) {
        Optional<Block> block = blockRepository.findById(id);
        return block.map(Block::getProducts).orElse(null);
    }

    @GetMapping("/shop/{id}")
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
}
