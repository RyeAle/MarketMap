package ru.gatchina.marketmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gatchina.marketmap.domain.Map;
import ru.gatchina.marketmap.domain.*;
import ru.gatchina.marketmap.repository.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class MarketMapApplication implements CommandLineRunner {

    private final static List<String> categories = new ArrayList<>();

    private final static java.util.Map<String, List<String>> products = new HashMap<>();

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NetworkRepository networkRepository;

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
        String cat1 = "Молочные продукты";
        String cat2 = "Хлебобулочные изделия";
        String cat3 = "Офощи и фрукты";
        String cat4 = "Мясные продукты";
        String cat5 = "Диссерт";

        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat4);
        categories.add(cat5);

        List<String> l1 = Arrays.asList("Молоко", "Сыр", "Кефир");
        products.put(cat1, l1);

        List<String> l2 = Arrays.asList("Хлеб", "Булка сдобная", "Пончик");
        products.put(cat2, l2);

        List<String> l3 = Arrays.asList("Огурцы", "Помидоры", "Яблоки", "Бананы");
        products.put(cat3, l3);

        List<String> l4 = Arrays.asList("Курица", "Говядина", "Свинина");
        products.put(cat4, l4);

        List<String> l5 = Arrays.asList("Торт", "Зефир", "Свинина");
        products.put(cat5, l5);

        int i = 0;
        for (String category : categories) {
            Category ct = new Category();
            ct.setName(category);
            ct.setLogoUrl("/img/categories/" + (++i));
            categoryRepository.save(ct);
        }
    }

    private void creatingProducts() {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            for (String prod : products.get(category.getName())) {
                Product product = new Product();
                product.setName(prod);
                product.setCategory(category);
                productRepository.save(product);
            }
        }
    }

    private void createNetwork() {
        Network network1 = new Network();
        network1.setName("Пятерочка");
        network1.setLogoUrl("/img/network/1.png");
        networkRepository.save(network1);

        Network network2 = new Network();
        network2.setName("Ашан");
        network2.setLogoUrl("/img/network/2.png");
        networkRepository.save(network2);

        Network network3 = new Network();
        network3.setName("Перекресток");
        network3.setLogoUrl("/img/network/3.png");
        networkRepository.save(network3);
    }

    private void createShop() {
        List<Network> networks = networkRepository.findAll();
        List<Product> products = productRepository.findAll();

        Shop shop1 = new Shop();
        shop1.setLatitude(59.937785);
        shop1.setLongitude(30.332520);
        shop1.setOpenTime(Time.valueOf(LocalTime.of(12, 0)));
        shop1.setCloseTime(Time.valueOf(LocalTime.of(2, 0)));
        shop1.setNetwork(networks.get(0));

        Shop shop2 = new Shop();
        shop2.setLatitude(59.940773);
        shop2.setLongitude(30.328132);
        shop2.setOpenTime(Time.valueOf(LocalTime.of(12, 0)));
        shop2.setCloseTime(Time.valueOf(LocalTime.of(1, 0)));
        shop2.setNetwork(networks.get(1));

        Shop shop3 = new Shop();
        shop3.setLatitude(59.942251);
        shop3.setLongitude(30.334666);
        shop3.setOpenTime(Time.valueOf(LocalTime.of(13, 0)));
        shop3.setCloseTime(Time.valueOf(LocalTime.of(0, 0)));
        shop3.setNetwork(networks.get(2));

        Shop shop4 = new Shop();
        shop4.setLatitude(59.936130);
        shop4.setLongitude(30.335696);
        shop4.setOpenTime(Time.valueOf(LocalTime.of(13, 0)));
        shop4.setCloseTime(Time.valueOf(LocalTime.of(1, 0)));
        shop4.setNetwork(networks.get(0));

        List<Shop> shops = new ArrayList<>();
        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop3);
        shops.add(shop4);

        Random random = new Random();
        List<Product> list = new ArrayList<>();
        for (Shop sh : shops) {
            for (int i = 0; i < 5; i++) {
                int j = random.nextInt(products.size());
                Product product = products.get(j);
                while (!list.contains(product)) {
                    list.add(product);
                }
            }
            sh.setProducts(new HashSet<>(list));
            list.clear();
            shopRepository.save(sh);
        }
    }

    private void createBlocks() {
        Map map = mapRepository.findAll().iterator().next();
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = productRepository.findAll();

        HashSet<Block> result = new HashSet<>();
        Block block = new Block(8, 0);
        block.setBlockType(BlockType.ENTRY);
        block.setMap(map);
        result.add(block);

        List<Block> passes = new ArrayList<>();
        passes.add(new Block(9, 0));
        for (int i = 0; i < 7; i++) {
            passes.add(new Block(8 + i, 1));
            passes.add(new Block(8 + i, 2));
        }

        for (int i = 0; i < 8; i++) {
            passes.add(new Block(14, 3 + i));
        }

        for (int i = 0; i < 12; i++) {
            passes.add(new Block(13 - i, 10));
        }

        for (int i = 0; i < 7; i++) {
            passes.add(new Block(12, 9 - i));
        }

        for (int i = 0; i < 9; i++) {
            passes.add(new Block(10 - i, 9));
        }

        for (int i = 0; i < 9; i++) {
            passes.add(new Block(10 - i, 5));
            passes.add(new Block(10 - i, 4));
        }

        for (int i = 0; i < 3; i++) {
            passes.add(new Block(2, 8 - i));
            passes.add(new Block(3, 8 - i));
            passes.add(new Block(9, 8 - i));
            passes.add(new Block(10, 8 - i));
        }

        for (int i = 0; i < 4; i++) {
            passes.add(new Block(5, 3 - i));
            passes.add(new Block(6, 3 - i));
        }
        passes.add(new Block(7, 0));

        for (Block b : passes) {
            b.setBlockType(BlockType.PASS);
            b.setMap(map);
        }

        List<Block> shelfs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Block bl = new Block(11 + i, 0);
            bl.setCategory(categories.stream().filter(c -> c.getName().
                    equals("Офощи и фрукты")).findFirst().get());
            bl.setProducts(products.stream().filter(p -> p.getCategory().getName().
                    equals("Офощи и фрукты")).collect(Collectors.toSet()));
            bl.setBlockType(BlockType.SHELF);
            bl.setMap(map);
            shelfs.add(bl);
        }

        for (int i = 0; i < 5; i++) {
            Block bl = new Block(15, 6 + i);
            bl.setCategory(categories.stream().filter(c -> c.getName().
                    equals("Диссерт")).findFirst().get());
            if (i != 1 && i != 4) {
                bl.setProducts(products.stream().filter(p -> p.getCategory().getName().
                        equals("Диссерт")).collect(Collectors.toSet()));
            } else {
                bl.setProducts(null);
            }
            bl.setBlockType(BlockType.SHELF);
            bl.setMap(map);
            shelfs.add(bl);
        }

        for (int i = 0; i < 5; i++) {
            Block bl = new Block(14 - i, 11);
            bl.setCategory(categories.stream().filter(c -> c.getName().
                    equals("Хлебобулочные изделия")).findFirst().get());
            if (i != 0 && i != 3) {
                bl.setProducts(products.stream().filter(p -> p.getCategory().getName().
                        equals("Хлебобулочные изделия")).collect(Collectors.toSet()));
            } else {
                bl.setProducts(null);
            }
            bl.setBlockType(BlockType.SHELF);
            bl.setMap(map);
            shelfs.add(bl);
        }

        for (int i = 0; i < 7; i++) {
            Block bl = new Block(10, 9 - i);
            bl.setCategory(categories.stream().filter(c -> c.getName().
                    equals("Мясные продукты")).findFirst().get());
            if (i != 0 && i != 3) {
                bl.setProducts(products.stream().filter(p -> p.getCategory().getName().
                        equals("Мясные продукты")).collect(Collectors.toSet()));
            } else {
                bl.setProducts(null);
            }
            bl.setBlockType(BlockType.SHELF);
            bl.setMap(map);
            shelfs.add(bl);
        }

        for (int i = 0; i < 3; i++) {
            Block bl1 = new Block(4, 3 - i);
            Block bl2 = new Block(7, 4 - i);
            bl1.setMap(map);
            bl2.setMap(map);
            bl1.setBlockType(BlockType.CASH);
            bl2.setBlockType(BlockType.CASH);
            result.add(bl1);
            result.add(bl2);
        }
        result.addAll(passes);
        result.addAll(shelfs);
        map.setBlocks(result);
        mapRepository.save(map);
    }

    private void createMap() {
        Shop shop = shopRepository.findAll().get(0);
        Map map = new Map();
        map.setWidth(20);
        map.setHeight(15);
        map.setFloor(1);

        map.setShop(shop);
        shop.getMaps().add(map);

        shopRepository.save(shop);
    }
}
