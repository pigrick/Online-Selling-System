package edu.mum.cs490.project;

import edu.mum.cs490.project.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addToCard(){

    }

}
