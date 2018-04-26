package edu.mum.cs490.project.model.form;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class GuestOrderForm extends CustomerOrderForm {


    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;

    public GuestOrderForm() {
    }

    public GuestOrderForm(@NotBlank String cardType, @NotBlank String cardHolderName, @CreditCardNumber String cardNumber, String last4Digit, @Pattern(regexp = "^((0[1-9])|(1[0-2]))\\/(\\d{2})$") String cardExpirationDate, @Pattern(regexp = "\\d{3}") String ccv, @Pattern(regexp = "\\d{5}") String cardZipcode, @Pattern(regexp = "\\d{10}") String phoneNumber, String street, String city, @NotNull String state, @Pattern(regexp = "\\d{5}") String zipcode, @NotBlank String firstName, @NotBlank String lastName, @Email String email) {
        super(cardType, cardHolderName, cardNumber, last4Digit, cardExpirationDate, ccv, cardZipcode, phoneNumber, street, city, state, zipcode);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
