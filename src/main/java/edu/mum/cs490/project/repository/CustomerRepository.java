package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Customer;
import edu.mum.cs490.project.domain.Vendor;
import org.springframework.stereotype.Repository;

/**
 * Created by Erdenebayar on 4/21/2018
 */
@Repository
public interface CustomerRepository extends UserRepository<Customer> {
}
