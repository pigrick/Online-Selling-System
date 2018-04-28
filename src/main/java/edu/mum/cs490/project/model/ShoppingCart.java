package edu.mum.cs490.project.model;

import edu.mum.cs490.project.domain.OrderDetail;

import java.util.List;

public class ShoppingCart {
    List<OrderDetail> orderDetails;

    public ShoppingCart(){}

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double calculateTotalPrice(){
        double sum = 0;
        for(OrderDetail orderdetail : this.orderDetails){
            sum += orderdetail.calculateTotalPrice();
        }
        return sum;
    }
}
