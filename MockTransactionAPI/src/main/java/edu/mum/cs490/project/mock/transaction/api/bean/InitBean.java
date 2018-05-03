/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.bean;

import edu.mum.cs490.project.mock.transaction.api.aop.TransactionAOPService;
import edu.mum.cs490.project.mock.transaction.api.dao.AccountDAO;
import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import edu.mum.cs490.project.mock.transaction.api.util.AES;
import edu.mum.cs490.project.mock.transaction.api.util.impl.AESImpl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author tuvshuu
 */
@Component
public class InitBean implements ApplicationRunner {

    @Value("${name}")
    private String author;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("<<< MOCK TRANSACTION API has been successfully started >>>");
        System.out.printf("<<< Author - %s >>>\n", author);
        insertData();
    }

    @Autowired
    AccountDAO accountDAO;
    @Autowired
    TransactionAOPService transactionAOPService;

    public void insertData() {
        Account accountOSS = new Account();
        accountOSS.setCardNo("4000300020001000");
        accountOSS.setCVV("100");
        accountOSS.setAmount(1.0);
        accountOSS.setExpirationDate("05/2020");
        accountOSS.setName("OSS");
        accountOSS.setZipCode("52557");
        accountOSS.setCreatedAt(new Date());
        accountDAO.save(accountOSS);

        Account accountTAX = new Account();
        accountTAX.setCardNo("4000300020002000");
        accountTAX.setCVV("200");
        accountTAX.setAmount(2.0);
        accountTAX.setExpirationDate("05/2020");
        accountTAX.setName("TAX");
        accountTAX.setZipCode("10000");
        accountTAX.setCreatedAt(new Date());
        accountDAO.save(accountTAX);

        Account accountV1 = new Account();
        accountV1.setCardNo("4000300020003001");
        accountV1.setCVV("301");
        accountV1.setAmount(3.0);
        accountV1.setExpirationDate("05/2020");
        accountV1.setName("V1");
        accountV1.setZipCode("52557");
        accountV1.setCreatedAt(new Date());
        accountDAO.save(accountV1);

        Account accountV2 = new Account();
        accountV2.setCardNo("4000300020003002");
        accountV2.setCVV("302");
        accountV2.setAmount(3.0);
        accountV2.setExpirationDate("05/2020");
        accountV2.setName("V2");
        accountV2.setZipCode("52557");
        accountV2.setCreatedAt(new Date());
        accountDAO.save(accountV2);

        Account account = new Account();
        account.setCardNo("4929127657563699");
        account.setCVV("123");
        account.setAmount(20000.0);
        account.setExpirationDate("04/2018");
        account.setName("YEE RICK");
        account.setZipCode("52557");
        account.setCreatedAt(new Date());
        accountDAO.save(account);
    }

    @Value("${api.secret.key.word}")
    private String apiSecredKeyWord;

    @Bean(name = "apiAES")
    public AES getApiAES() {
        System.out.println("apiSecredKeyWord - " + apiSecredKeyWord);
        return new AESImpl(apiSecredKeyWord);
    }

    @Value("${secret.key.word}")
    private String secredKeyWord;

    @Bean(name = "AES")
    public AES getAES() {
        System.out.println("secredKeyWord - " + secredKeyWord);
        return new AESImpl(secredKeyWord);
    }
}
