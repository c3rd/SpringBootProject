package dev.c3rd.app.service.user.impl;

import dev.c3rd.app.exception.user.UserNotFoundException;
import dev.c3rd.app.model.user.User;
import dev.c3rd.app.repository.UserRepository;
import dev.c3rd.app.service.user.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public User updateUser(User user, Long id) {

        User existingUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));

        existingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());

        repository.save(existingUser);

        return existingUser;

    }

    @Override
    public void deleteUser(Long id) {
        repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        repository.deleteById(id);
    }

}
