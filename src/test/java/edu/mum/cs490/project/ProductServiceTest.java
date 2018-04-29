package edu.mum.cs490.project;

import edu.mum.cs490.project.domain.Product;
import edu.mum.cs490.project.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(Runner.class)
public class ProductServiceTest {

    @MockBean
    private ProductService productService;

    @Test
    public void getOneTest(Integer id) throws Exception{

        List<Product> product= new ArrayList();




    }

}
