package edu.mum.cs490.project.repository;

import edu.mum.cs490.project.domain.CardDetail;
import edu.mum.cs490.project.domain.Guest;
import edu.mum.cs490.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardDetailRepository extends JpaRepository<CardDetail, Integer> {

    CardDetail findByOwner(User owner);

    CardDetail findByGuest(Guest guest);

}
