package edu.mum.cs490.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.mum.cs490.project.model.payment.mock.TransactionRequest;
import edu.mum.cs490.project.service.PaymentService;
import edu.mum.cs490.project.utils.HttpSender;
import edu.mum.cs490.project.utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidParameterException;

@Service
public class MockPaymentServiceImpl implements PaymentService {

    @Autowired
    private HttpSender httpSender;


    @Override
    public Integer purchase(String txnId, String srcCardNo, String expirationDate, String nameOnCard, String CVV, String zipCode, Double amount, String dstCardNo) {

        System.out.println("PaymentService purchase method is called.");

        TransactionRequest transactionRequest = new TransactionRequest(txnId, srcCardNo, expirationDate, nameOnCard.toUpperCase(), CVV, zipCode, amount, dstCardNo);

        System.out.println("Request data : " + transactionRequest.toString());

        String requestData;
        try {
            requestData = JsonConverter.<TransactionRequest>objectToJson(transactionRequest);
        } catch (JsonProcessingException e) {
            throw new InvalidParameterException("Invalid parameter!");
        }
        Integer responseCode;
        try {
            String responseData = httpSender.doPostTransactionToApi(requestData);
            try {
                responseCode = Integer.parseInt(responseData);
            } catch (NumberFormatException e) {
                System.err.println("External system error! " + responseData);
                responseCode = 500;
            }
        } catch (IOException e) {
            System.err.println("Connection error! " + e.getMessage());
            responseCode = 999;
        }

        System.out.println("Result of purchase method is " + responseCode);
        return responseCode;
    }


    public static void main(String[] args) {
        try {
            TransactionRequest transactionRequest = new TransactionRequest("", "", "", "", "", "", 10000.0, "");
            System.out.println(JsonConverter.<TransactionRequest>objectToJson(transactionRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
