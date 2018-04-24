package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Vendor;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Erdenebayar on 4/21/2018
 */
@Repository
public interface VendorRepository extends UserRepository<Vendor> {
}
