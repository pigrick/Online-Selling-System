package edu.mum.cs490.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private Guest guest;
    private String cardType;
    private String cardHolderName;
    private String cardNumber;
    private String last4Digit;
    private String cardExpirationDate;
    private String cvv;
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private Status status;

    public CardDetail(Guest owner, String cardType, String cardHolderName, String cardNumber, String last4Digit, String cardExpirationDate, String cvv, String zipcode) {
        this.guest = owner;
        this.cardType = cardType;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.last4Digit = last4Digit;
        this.cardExpirationDate = cardExpirationDate;
        this.cvv = cvv;
        this.zipcode = zipcode;
    }

    public CardDetail(User owner, String cardType, String cardHolderName, String cardNumber, String last4Digit, String cardExpirationDate, String cvv, String zipcode) {
        this.owner = owner;
        this.cardType = cardType;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.last4Digit = last4Digit;
        this.cardExpirationDate = cardExpirationDate;
        this.cvv = cvv;
        this.zipcode = zipcode;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public CardDetail(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLast4Digit() {
        return last4Digit;
    }

    public void setLast4Digit(String last4Digit) {
        this.last4Digit = last4Digit;
    }
}
