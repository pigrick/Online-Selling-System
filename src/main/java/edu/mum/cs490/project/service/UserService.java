package edu.mum.cs490.project.service;

import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService extends UserDetailsService{

    public List<User> getAll();
}
