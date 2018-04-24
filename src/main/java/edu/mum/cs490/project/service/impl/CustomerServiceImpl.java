package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.repository.CustomerRepository;
import edu.mum.cs490.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Service
public class CustomerServiceImpl extends UserServiceImpl<Customer> implements CustomerService{

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
