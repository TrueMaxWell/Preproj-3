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
    public User loadUserByUsername(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where email = :email");
        query.setParameter("email",email);
        return (User) query.uniqueResult();
    }

    @Bean
    public void createAdmin() {
        try {
            loadUserByUsername("admin@mail.ru").isEnabled();
        } catch (Exception e) {
            Collection<Role> roles = new ArrayList<>();
            Role roleAdmin = new Role();
            roleAdmin.setRole("ROLE_ADMIN");
            roles.add(roleAdmin);
            Role roleUser = new Role();
            roleUser.setRole("ROLE_USER");
            roles.add(roleUser);
            User admin = new User();
            admin.setRoles(roles);
            admin.setEmail("admin@mail.ru");
            admin.setPassword("admin");
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setAge(35L);
            sessionFactory.getCurrentSession().saveOrUpdate(admin);
        }



    }
}