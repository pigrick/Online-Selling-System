package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(Integer id);

    User getByUsername(String username);

  /*  @Modifying
    @Query("UPDATE User AS u SET u.password = :#{#user.password} WHERE u.id = :#{#user.id}")
    void updatePassword(@Param("user") User user);*/



}
