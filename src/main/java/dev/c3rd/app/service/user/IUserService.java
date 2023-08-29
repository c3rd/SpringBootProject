package dev.c3rd.app.service.user;

import dev.c3rd.app.model.user.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User saveUser(User user);
    User getUserById(Long id);
    User updateUser(User user, Long id);
    void deleteUser(Long id);

}
