
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    ShopRepository shopRepository = new ShopRepository();
    Product product1 = new Product(67, "Шалтай-Болтай", 1_765);
    Product product2 = new Product(105, "Куртка", 3_450);
    Product product3 = new Product(15, "Пылесос", 3_500);
    Product product4 = new Product(15, "Планшет", 25_000);
    Product product5 = new Product(84, "Телевизор", 35_000);

    @BeforeEach
    public void preparation() {
        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);
    }

    @Test
    public void functionRemoveByIdTestFirst() {
        shopRepository.removeById(105);

        Product[] expected = {product1, product3};
        Product[] actual = shopRepository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void functionRemoveByIdTestSecond() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepository.removeById(365);
        });
    }

    @Test
    public void functionAddTestThird() {
        shopRepository.add(product5);
        Product[] expected = {product1, product2, product3, product5};
        Product[] actual = shopRepository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void functionAddTestFourth() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shopRepository.add(product4);
        });
    }
}
