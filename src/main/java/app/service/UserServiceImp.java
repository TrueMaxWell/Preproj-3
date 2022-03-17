package app.service;

import app.repository.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void changeUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        return user;
    }

    @PostConstruct
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
            userDao.save(admin);
        }
    }

    public void setRole(User user,String role) {
        Collection<Role> roles = new ArrayList<>();
        if (Objects.equals(role, "ROLE_ADMIN")) {
            Role roleAdmin = new Role();
            roleAdmin.setRole("ROLE_ADMIN");
            roles.add(roleAdmin);
        }
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        roles.add(roleUser);
        user.setRoles(roles);
    }
}
