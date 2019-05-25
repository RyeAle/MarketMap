package ru.gatchina.marketmap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketMapApplicationTests {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NetworkRepository networkRepository;

    @Test
    public void testCreatingCategory() {
        Category category1 = new Category();
        category1.setName("Молочные продукты");

        Category category2 = new Category();
        category2.setName("Хлебобулочные изделия");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @Test
    public void testProductCreating() {
        List<Category> categories = categoryRepository.findAll();
        Assert.assertFalse(categories.isEmpty());

        Product product1 = new Product();
        product1.setName("Молоко");
        System.out.println(categories.get(0).getName());
        product1.setCategory(categories.get(0));

        Product product2 = new Product();
        product2.setName("Хлеб");
        System.out.println(categories.get(1).getName());
        product2.setCategory(categories.get(1));

        productRepository.save(product1);
        productRepository.save(product2);
    }

    @Test
    public void testNetworkCreating() {
        Network network = new Network();
        network.setName("Пятерочка");
        networkRepository.save(network);
    }

    @Test
    public void testShopCreating() {
        Shop shop = new Shop();
        shop.setLongitude(1.23);
        shop.setLatitude(1.23);

        List<Product> products = productRepository.findAll();
        shop.setProducts(new HashSet<>(products));
        shopRepository.save(shop);
    }

    @Test
    public void testShop() {
        Shop shop = shopRepository.findAll().get(0);
        Assert.assertNotNull(shop.getProducts());
        System.out.println(shop.getProducts());
    }
}
