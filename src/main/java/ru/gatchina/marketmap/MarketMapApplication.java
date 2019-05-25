package ru.gatchina.marketmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gatchina.marketmap.domain.Category;
import ru.gatchina.marketmap.domain.Network;
import ru.gatchina.marketmap.domain.Product;
import ru.gatchina.marketmap.domain.Shop;
import ru.gatchina.marketmap.repository.CategoryRepository;
import ru.gatchina.marketmap.repository.NetworkRepository;
import ru.gatchina.marketmap.repository.ProductRepository;
import ru.gatchina.marketmap.repository.ShopRepository;

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

    public static void main(String[] args) {
        SpringApplication.run(MarketMapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        creatingCategory();
        creatingProducts();
        createNetwork();
        createShop();
    }

    private void creatingCategory() {
        Category category1 = new Category();
        category1.setName("Молочные продукты");

        Category category2 = new Category();
        category2.setName("Хлебобулочные изделия");
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
}
