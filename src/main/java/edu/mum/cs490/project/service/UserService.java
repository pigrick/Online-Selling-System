package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService<T extends User> extends UserDetailsService{

    T getById(Integer id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<T> getAll();

    @Transactional
    @PreAuthorize("#user.id == principal.id or hasRole('ROLE_ADMIN')")
    T  saveOrUpdate(T user);

    @Transactional
    void delete(Integer id);

    @Transactional
    void changeStatus(Integer id, Status status);
}
