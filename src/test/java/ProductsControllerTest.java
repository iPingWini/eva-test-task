import com.zagor.configs.Application;
import com.zagor.controllers.ProductsController;
import com.zagor.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturn400NotPresented() throws Exception {

        mockMvc.perform(get("/shop/product")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testReturn400Empty() throws Exception {

        mockMvc.perform(get("/shop/product?nameFilter=")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testReturn200() throws Exception {

        mockMvc.perform(get("/shop/product?nameFilter=^.*[eva].*$")).andDo(print()).andExpect(status().isOk());
    }
}
