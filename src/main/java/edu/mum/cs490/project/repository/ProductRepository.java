package edu.mum.cs490.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.mum.cs490.project.domain.Product;

import java.util.List;

/**
 * Created by Pagmaa on 4/23/2018
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

   Product getById(Integer id);
}
