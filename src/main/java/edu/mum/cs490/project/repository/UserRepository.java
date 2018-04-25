package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository<T extends User> extends JpaRepository<T, Integer> {

    T getByUsername(String username);

  /*  @Modifying
    @Query("UPDATE User AS u SET u.password = :#{#user.password} WHERE u.id = :#{#user.id}")
    void updatePassword(@Param("user") User user);*/
}
