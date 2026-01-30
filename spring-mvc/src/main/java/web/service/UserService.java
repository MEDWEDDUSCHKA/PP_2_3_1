package web.service;


import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void updateUser(Long id, String firstName, String lastName, String email);
    void updateUserFromEdit(Long id, String firstName, String lastName, String email);
    void deleteUser(Long id);
    List<User> findAllUsers();
    User findUserById(Long id);
}
