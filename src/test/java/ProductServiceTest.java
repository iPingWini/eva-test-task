import com.zagor.configs.Application;
import com.zagor.dao.ProductDao;
import com.zagor.entities.Product;
import com.zagor.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceTest {

    @MockBean
    private ProductDao productDao;

    @Autowired
    private ProductService productService;

    @Test
    public void testGetAllProductsWithFilter() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setName("Мыло");
        product.setDescription("Описание");
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Щетка");
        product2.setDescription("Описание");
        products.add(product);
        products.add(product2);

        given(this.productDao.getProducts())
                .willReturn(products);

        List<Product> answer = productService.getAllProductsWithFilter("^.*[М].*$");

        assertThat(answer.get(0)).isEqualTo(products.get(1));

    }

    @Test
    public void testGetAllProductsWithWrongFilter() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setName("Мыло");
        product.setDescription("Описание");
        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Сыр");
        product2.setDescription("Описание");
        products.add(product);
        products.add(product2);

        given(this.productDao.getProducts())
                .willReturn(products);

        List<Product> answer = productService.getAllProductsWithFilter("^.*[ы].*$");

        assertThat(answer).isEmpty();

    }
}
