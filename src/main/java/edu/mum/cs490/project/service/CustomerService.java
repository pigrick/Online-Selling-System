package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Address;
import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Status;

import java.util.List;
import edu.mum.cs490.project.domain.User;

import java.util.List;

public interface CustomerService extends UserService<Customer>{

    public List<Customer> find(String username, String firstName, String lastName, Status status);

    public List<Address> findByUser_id(Integer userId);
}
