package edu.mum.cs490.project.model.form;

import edu.mum.cs490.project.domain.Address;
import edu.mum.cs490.project.domain.CardDetail;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class CustomerOrderForm implements Serializable {
    @NotBlank
    private String cardType;
    @NotBlank
    private String cardHolderName;
    @CreditCardNumber
    private String cardNumber;
    private String last4Digit;
    @Pattern(regexp = "^((0[1-9])|(1[0-2]))\\/(\\d{2})$")
    private String cardExpirationDate;
    @Pattern(regexp = "\\d{3}")
    private String ccv;
    @Pattern(regexp = "\\d{5}")
    private String cardZipcode;

    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;
    private String street;
    private String city;
    @NotNull
    private String state;
    @Pattern(regexp = "\\d{5}")
    private String zipcode;

    public CustomerOrderForm(){}

    public CustomerOrderForm(@NotBlank String cardType, @NotBlank String cardHolderName, @CreditCardNumber String cardNumber, String last4Digit, @Pattern(regexp = "^((0[1-9])|(1[0-2]))\\/(\\d{2})$") String cardExpirationDate, @Pattern(regexp = "\\d{3}") String ccv, @Pattern(regexp = "\\d{5}") String cardZipcode, @Pattern(regexp = "\\d{10}") String phoneNumber, String street, String city, @NotNull String state, @Pattern(regexp = "\\d{5}") String zipcode) {
        this.cardType = cardType;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.last4Digit = last4Digit;
        this.cardExpirationDate = cardExpirationDate;
        this.ccv = ccv;
        this.cardZipcode = cardZipcode;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
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

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getCardZipcode() {
        return cardZipcode;
    }

    public void setCardZipcode(String cardZipcode) {
        this.cardZipcode = cardZipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
