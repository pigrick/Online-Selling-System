package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.repository.UserRepository;
import edu.mum.cs490.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = getById(id);
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        User user = getById(id);
        user.setStatus(status);
        userRepository.save(user);
    }
}
