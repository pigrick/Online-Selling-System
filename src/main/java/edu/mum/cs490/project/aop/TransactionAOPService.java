/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.aop;

import edu.mum.cs490.project.utils.AESConverter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author tuvshuu
 */
@Aspect
@Component
public class TransactionAOPService {

    @Autowired
    private AESConverter aesConverter;

    /**
     * the method is called between OSS and ExternalTransactionAPI
     *
     * @param pjp
     * @param data
     * @return
     * @throws Throwable
     */
    @Around("execution(* edu.mum.cs490.project.utils.HttpSender.doPostTransactionToApi(..))&& args(data)")
    public Object aopEncryptDecryptService(ProceedingJoinPoint pjp, String data) throws Throwable {
        System.out.println("# AOP BEFORE (5) #  is called on " + pjp.getSignature().toShortString() + " " + data);
        String encryptedData;
        try {
            encryptedData = aesConverter.encrypt(data);
        } catch (Exception e) {
            encryptedData = e.getMessage();
        }
        System.out.println("encrypting data - " + (encryptedData != null ? encryptedData : null));
        Object retVal = pjp.proceed(new Object[]{encryptedData});
        System.out.println("# AOP AFTER (5) #  is called on " + pjp.getSignature().toShortString() + " returnValue - " + (retVal != null ? retVal.toString() : null));
        retVal = aesConverter.decrypt(retVal != null ? retVal.toString() : "null");
        System.out.println("encrypted result - " + retVal);
        return retVal;
    }
}
