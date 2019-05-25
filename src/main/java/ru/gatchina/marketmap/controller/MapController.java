package ru.gatchina.marketmap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gatchina.marketmap.domain.Block;
import ru.gatchina.marketmap.domain.Map;
import ru.gatchina.marketmap.domain.Product;
import ru.gatchina.marketmap.domain.Shop;
import ru.gatchina.marketmap.repository.BlockRepository;
import ru.gatchina.marketmap.repository.MapRepository;
import ru.gatchina.marketmap.repository.ShopRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/map")
public class MapController {

    private MapRepository mapRepository;
    private BlockRepository blockRepository;
    private ShopRepository shopRepository;

    @GetMapping("/shop")
    public Map getMap(@RequestParam int id) {
        Optional<Map> mapHolder = mapRepository.findById(id);
        return mapHolder.orElse(null);
    }

    @GetMapping("/shelf")
    public Set<Product> getBlock(@RequestParam int id) {
        Block block = blockRepository.findById(id).orElse(null);

        return block != null ? block.getProducts() : Collections.emptySet();
    }

    @GetMapping("/shop")
    public Shop getShopInfo(@RequestParam int id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Autowired
    public void setMapRepository(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @Autowired
    public void setBlockRepository(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
}
