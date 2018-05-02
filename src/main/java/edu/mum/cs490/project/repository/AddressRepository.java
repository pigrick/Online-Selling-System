package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Address;
import edu.mum.cs490.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    public List<Address> findByUser_id(Integer userId);
}
