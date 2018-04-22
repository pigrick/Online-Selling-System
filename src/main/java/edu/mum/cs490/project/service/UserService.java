package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService extends UserDetailsService{

    User getById(Integer id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> getAll();

    @Transactional
    @PreAuthorize("#user.id == principal.id or hasRole('ROLE_ADMIN')")
    User saveOrUpdate(User user);

    @Transactional
    void delete(Integer id);

    @Transactional
    void changeStatus(Integer id, Status status);
}
