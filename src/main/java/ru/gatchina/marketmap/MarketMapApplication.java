package ru.gatchina.marketmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gatchina.marketmap.domain.*;
import ru.gatchina.marketmap.repository.*;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class MarketMapApplication implements CommandLineRunner {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private MapRepository mapRepository;

    public static void main(String[] args) {
        SpringApplication.run(MarketMapApplication.class, args);
    }

    @Override
    public void run(String... args) {
        creatingCategory();
        creatingProducts();
        createNetwork();
        createShop();
        createMap();
        createBlocks();
    }

    private void creatingCategory() {
        Category category1 = new Category();
        category1.setName("Молочные продукты");
        category1.setLogoUrl("/img/categories/1.png");

        Category category2 = new Category();
        category2.setName("Хлебобулочные изделия");
        category2.setLogoUrl("/img/categories/2.png");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    private void creatingProducts() {
        List<Category> categories = categoryRepository.findAll();

        Product product1 = new Product();
        product1.setName("Молоко");
        product1.setCategory(categories.get(0));

        Product product2 = new Product();
        product2.setName("Хлеб");
        product2.setCategory(categories.get(1));

        productRepository.save(product1);
        productRepository.save(product2);
    }

    private void createNetwork() {
        Network network = new Network();
        network.setName("Пятерочка");
        network.setLogoUrl("/img/network/1.png");
        networkRepository.save(network);
    }

    private void createShop() {
        Shop shop = new Shop();
        shop.setLongitude(1.23);
        shop.setLatitude(1.23);
        shop.setNetwork(networkRepository.findAll().get(0));

        List<Product> products = productRepository.findAll();
        shop.setProducts(new HashSet<>(products));
        shopRepository.save(shop);
    }

    private void createBlocks() {
        Map map = mapRepository.findAll().iterator().next();
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = productRepository.findAll();

        Block block1 = new Block();
        block1.setX(1);
        block1.setY(2);
        block1.setMap(map);
        block1.setCategory(categories.get(0));
        block1.setProducts(new HashSet<>(products));
        block1.setBlockType(BlockType.PASS);

        Block block2 = new Block();
        block2.setX(2);
        block2.setY(3);
        block2.setMap(map);
        block2.setProducts(new HashSet<>(products));
        block2.setCategory(categories.get(1));
        block2.setBlockType(BlockType.SHELF);

       blockRepository.save(block1);
       blockRepository.save(block2);
    }

    private void createMap() {
        Shop shop = shopRepository.findAll().get(0);
        Map map = new Map();
        map.setWidth(100);
        map.setHeight(200);
        map.setFloor(1);

        map.setShop(shop);
        shop.getMaps().add(map);

        shopRepository.save(shop);
    }
}
