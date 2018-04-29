package edu.mum.cs490.project.service;

import javax.management.BadAttributeValueExpException;

public interface PaymentService {
    /**
     *
     * @param txnId
     * @param srcCardNo - SourceCardNo
     * @param expirationDate
     * @param nameOnCard
     * @param CVV
     * @param zipCode
     * @param amount
     * @param dstCardNo - DestinationCardNo
     * @return 1 - success, 2 - not found error, 3 - not enough money, 500 - external system error, 999 - connection error
     * @throws BadAttributeValueExpException
     */
    Integer purchase(String txnId, String srcCardNo, String expirationDate, String nameOnCard, String CVV, String zipCode, Double amount, String dstCardNo);
}
