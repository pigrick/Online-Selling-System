package edu.mum.cs490.project.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String status;
    @OneToMany
    private List<Address> addresses;
    @OneToMany(mappedBy = "owner")
    private List<CardDetail> cards;

    public User(){

    }

    public List<CardDetail> getCards() {
        return cards;
    }

    public void setCards(List<CardDetail> cards) {
        this.cards = cards;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
