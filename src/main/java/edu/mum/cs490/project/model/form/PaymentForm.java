package edu.mum.cs490.project.model.form;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class PaymentForm implements Serializable {

    private Integer cardId;
    private String cardType;
    @NotBlank
    private String cardHolderName;
    private String cardNumber;
    private String last4Digit;
    @Pattern(regexp = "^((0[1-9])|(1[0-2]))\\/(\\d{2})$")
    private String cardExpirationDate;
    @Pattern(regexp = "\\d{3}")
    private String cvv;
    @Pattern(regexp = "\\d{5}")
    private String cardZipcode;

    public PaymentForm(){}

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getLast4Digit() {
        return last4Digit;
    }

    public void setLast4Digit(String last4Digit) {
        this.last4Digit = last4Digit;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardZipcode() {
        return cardZipcode;
    }

    public void setCardZipcode(String cardZipcode) {
        this.cardZipcode = cardZipcode;
    }
}
