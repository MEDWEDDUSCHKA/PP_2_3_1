package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(Long id, String firstName, String lastName, String email) {
        User existingUser = userDao.findUserById(id);
        if (existingUser != null) {
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setEmail(email);
            userDao.updateUser(existingUser);
        }
    }

    @Override
    @Transactional
    public void updateUserFromEdit(Long id, String firstName, String lastName, String email) {
        User existingUser = userDao.findUserById(id);
        if (existingUser == null) {
            User newUser = new User(firstName, lastName, email);
            userDao.saveUser(newUser);
        } else {
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setEmail(email);
            userDao.updateUser(existingUser);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

}
