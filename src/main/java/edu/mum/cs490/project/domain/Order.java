package edu.mum.cs490.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="`order`", indexes = {@Index(columnList = "customer_id", name = "customer_idx")})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToOne
    private Guest guest;
    @OneToOne(cascade={ CascadeType.PERSIST, CascadeType.MERGE})
    private CardDetail card;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date shippingDate;
    @OneToOne(cascade={ CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "order", cascade={ CascadeType.PERSIST} )
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Customer customer, Address address, List<OrderDetail> orderDetails) {
        this.customer = customer;
        this.address = address;
        this.orderDetails = orderDetails;
        this.status = Status.ENABLED;
    }

    public Order(Guest guest, Address address, List<OrderDetail> orderDetails) {
        this.guest = guest;
        this.address = address;
        this.orderDetails = orderDetails;
        this.status = Status.ENABLED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CardDetail getCard() {
        return card;
    }

    public void setCard(CardDetail card) {
        this.card = card;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public double getTotalPriceWithoutTax(){
        double sum = 0;
        for(OrderDetail od : this.orderDetails){
            sum = sum + (od.getPrice() * od.getQuantity());
        }
        return sum;
    }

    public double getTax(){
        return getTotalPriceWithoutTax() * 0.07;
    }

    public double getTotalPriceWithTax(){
        return getTotalPriceWithoutTax() * 0.08;
    }
}
