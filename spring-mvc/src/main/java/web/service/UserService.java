package web.service;


import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> findAllUsers();
    User findUserById(Long id);
}
