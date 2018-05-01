package edu.mum.cs490.project.service.impl;

import edu.mum.cs490.project.domain.Status;
import edu.mum.cs490.project.domain.User;
import edu.mum.cs490.project.repository.UserRepository;
import edu.mum.cs490.project.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Erdenebayar on 4/20/2018
 */
@Primary
@Service
public class UserServiceImpl<T extends User> implements UserService<T> {

    private final UserRepository<T> userRepository;

    public UserServiceImpl(UserRepository<T> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public T getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existByIdNotAndUsername(Integer id, String username) {
        return userRepository.existByIdNotAndUsername(id, username);
    }

    @Override
    public List<T> getAll() {
        return userRepository.findAll();
    }

    @Override
    public T saveOrUpdate(T user) {
        return userRepository.save(user);
    }

    @Override
    public T loadUserByUsername(String username) throws UsernameNotFoundException {
        T user = userRepository.getByUsername(username);
        if (user != null)
            return user;
        else
            throw new UsernameNotFoundException("Username not found");
    }

    @Override
    public void delete(Integer id) {
        changeStatus(id, Status.DELETED);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        T user = getById(id);
        user.setStatus(status);
        userRepository.save(user);
    }
}
