package edu.mum.cs490.project.mock.transaction.api.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.ws.rs.core.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class TransactionControllerTest {
    private static final String AUTH = "Authorization";
    private static final String BASIC = "Basic dXNlcjpwYXNz";
    private static final String URL = "/mock/transaction/api";

    @Autowired
    private MockMvc mvc;

    @Test
    public void testUnauthorizated() throws Exception {
        System.out.println("Testing testUnauthorizated() header authentication");
        this.mvc.perform(MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andExpect(content().string(""));
    }

    @Test
    public void testAuthorizated() throws Exception {
        System.out.println("Testing testAuthorizated() header authentication");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                .content("cjDqlhaq3X/L6IvCGzHwFA7arPolAMVhvLdH6XRWKxv3tkGzd4sAhorKrFUyw/Vib7Yp9WPJNAu18xVwKy9OL3GoC1fE15X8Y2dFV74COGqUUXhzSeOzccvEHiXJPwxSDY6p9tH8NbnlL5drGodZp+GYas1NXZusW1rDz2WwaM4TB8MqqzZ8SVaH8zpko9NG"))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithoutSrcAccount() throws Exception {
        System.out.println("Testing testWithoutSrcAccount()");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                //{"txnId":"000","expirationDate":"05/2020","nameOnCard":"TEST","zipCode":"52557","amount":100,"dstCardNo":"4000300020001000","cvv":"123"}
                .content("cjDqlhaq3X/L6IvCGzHwFA7arPolAMVhvLdH6XRWKxv3tkGzd4sAhorKrFUyw/Vib7Yp9WPJNAu18xVwKy9OL3GoC1fE15X8Y2dFV74COGqUUXhzSeOzccvEHiXJPwxSDY6p9tH8NbnlL5drGodZp+GYas1NXZusW1rDz2WwaM4TB8MqqzZ8SVaH8zpko9NG"))
                .andExpect(status().isOk())
                // 400
                .andExpect(content().string("sxIouSXU3uVAyleMuMzgxA=="));
    }

    @Test
    public void testEmptySrcAccount() throws Exception {
        System.out.println("Testing testEmptySrcAccount()");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                //{"txnId":"000","srcCardNo":"","expirationDate":"05/2020","nameOnCard":"TEST","zipCode":"52557","amount":100,"dstCardNo":"4000300020001000","cvv":"123"}
                .content("cjDqlhaq3X/L6IvCGzHwFFeAtrooQn46MmoSGJZe+YLPulALOMpP4+1VsPvFEjc3OLBgL/rXkMnfpwbgDc4NFm+Amvd6FDnmnfSaR4R1K6nyIK2H23wKrXpyNlkmsMNWSnDOXGMlt4tXXG/1TLYgLGNwFsH6nXT2t45CVVf8tnVrlVR8wx2DkLEpr+nHFQ7bYOAoBN9/mq4BfYUc9aIhaQ=="))
                .andExpect(status().isOk())
                // 400
                .andExpect(content().string("sxIouSXU3uVAyleMuMzgxA=="));
    }

    @Test
    public void testNotFoundSrcAccount() throws Exception {
        System.out.println("Testing testNotFoundSrcAccount()");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                //{"txnId":"000","srcCardNo":"4929127657563690","expirationDate":"05/2020","nameOnCard":"TEST","zipCode":"52557","amount":100,"dstCardNo":"4000300020001000","cvv":"123"}
                .content("cjDqlhaq3X/L6IvCGzHwFB8OBblSbotFmKZJQWE+fB3XL4kZG26ukT2k4T6W/2XZz7pQCzjKT+PtVbD7xRI3NziwYC/615DJ36cG4A3ODRZvgJr3ehQ55p30mkeEdSup8iCth9t8Cq16cjZZJrDDVkpwzlxjJbeLV1xv9Uy2ICxjcBbB+p109reOQlVX/LZ1a5VUfMMdg5CxKa/pxxUO22DgKATff5quAX2FHPWiIWk="))
                .andExpect(status().isOk())
                // 2
                .andExpect(content().string("wkRFjk7nC0mNnaXaXViMzA=="));
    }

    @Test
    public void testSuccess() throws Exception {
        System.out.println("Testing testSuccess()");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                //{"txnId":"00011","srcCardNo":"4000300020001000","expirationDate":"05/2020","nameOnCard":"OSS","zipCode":"52557","amount":1,"cvv":"100","dstCardNo":"4000300020001001"}
                .content("WtHW82Xa9eCX35P64rp7jHCkp182+Voq5ylduR/qzQfngw0OkYKSGZYksDPXETI/1uD9Sz4gZRS8oG7YtwYN9SNwAO7nRW3daMdbEpnj4hTr1uNsl9ZRu+cVvaXLiR34cagLV8TXlfxjZ0VXvgI4asGygoZ2+ACjlZCrASd555H0lMVxV6bqKHhw3zFEk65RmUC9JsDGircsajS0SW6ql2Qvz4c1tv3JJ5pDCzZluOY="))
                .andExpect(status().isOk())
                // 1
                .andExpect(content().string("xtIwuiMndZeTaUgq6QC7Og=="));
    }

    @Test
    public void testNotEnoughSrcAccount() throws Exception {
        System.out.println("Testing testNotEnoughSrcAccount()");
        this.mvc.perform(MockMvcRequestBuilders.post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTH, BASIC)
                //{"txnId":"00011","srcCardNo":"4000300020002000","expirationDate":"05/2020","nameOnCard":"TAX","zipCode":"10000","amount":10000,"cvv":"200","dstCardNo":"4000300020001001"}
                .content("WtHW82Xa9eCX35P64rp7jHCkp182+Voq5ylduR/qzQfB2S0yEswG/VzaRj/dAYB91uD9Sz4gZRS8oG7YtwYN9SNwAO7nRW3daMdbEpnj4hSoCBzrPFBCYPPB3z+rcWO70baH0AUt1QW+3Db7o7hJE/hUUm0iT3nc9HWqTN1ezvmJjUQW2v6BSuilUmJFLAVTLC2wICy+Xu/mIQdk44mQGWuqiKFh9QUb/ks/062gx18="))
                .andExpect(status().isOk())
                // 3
                .andExpect(content().string("T0+JzonrVFAaAGE3OqmKCw=="));
    }
}
