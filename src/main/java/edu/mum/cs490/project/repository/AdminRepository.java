package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin getByUsername(String username);

}
