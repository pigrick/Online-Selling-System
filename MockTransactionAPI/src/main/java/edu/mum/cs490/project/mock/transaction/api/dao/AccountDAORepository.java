package edu.mum.cs490.project.mock.transaction.api.dao;

import edu.mum.cs490.project.mock.transaction.api.entity.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AccountDAORepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Account mergeAccount(Account account) {
        return entityManager.merge(account);
    }
}
