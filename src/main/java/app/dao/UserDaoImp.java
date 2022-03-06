package app.dao;

import app.model.Role;
import app.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void removeUser(Long id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from User where id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void changeUser(User user) {
        sessionFactory.getCurrentSession().update(user);

    }

    @Override
    public User getUser(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
        query.setParameter("id",id);
        return (User) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User loadUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
        query.setParameter("username",username);
        return (User) query.uniqueResult();
    }

    @Bean
    public void createAdmin() {
        try {
            loadUserByUsername("admin").isEnabled();
        } catch (Exception e) {
            Collection<Role> roles = new ArrayList<>();
            Role role = new Role();
            role.setRole("ROLE_ADMIN");
            roles.add(role);
            User admin = new User();
            admin.setRoles(roles);
            admin.setUsername("admin");
            admin.setPassword("admin");
            sessionFactory.getCurrentSession().saveOrUpdate(admin);
        }



    }
}