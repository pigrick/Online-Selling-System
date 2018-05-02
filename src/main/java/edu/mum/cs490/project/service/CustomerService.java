package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Status;

import java.util.List;

public interface CustomerService extends UserService<Customer>{

    public List<Customer> find(String username, String firstName, String lastName, Status status);
}
