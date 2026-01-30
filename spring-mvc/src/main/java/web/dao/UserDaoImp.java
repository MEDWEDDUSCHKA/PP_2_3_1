package web.dao;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_FIND_ALL_USERS = "SELECT u FROM User u";

    @Override
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user){
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(Long id){
        User user = entityManager.find(User.class, id);
        if(user != null){
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> findAllUsers() {
        TypedQuery <User> query = entityManager.createQuery(QUERY_FIND_ALL_USERS, User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
