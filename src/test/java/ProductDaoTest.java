import com.zagor.configs.Application;
import com.zagor.dao.ProductDao;
import com.zagor.dao.impl.ProductDaoImpl;
import com.zagor.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductDaoTest {
    @MockBean
    ProductDaoImpl productDaoImpl;

    @Autowired
    ProductDao productDao;

    @Test
    public void testGetProducts() {
        Product product = new Product();
        product.setId(1);
        product.setName("Мыло");
        product.setDescription("Описание");

        given(this.productDao.getProducts())
                .willReturn(Arrays.asList(product));

        Product answerProduct = productDao.getProducts().get(0);

        assertThat(answerProduct.getId()).isEqualTo(1);

    }
}
