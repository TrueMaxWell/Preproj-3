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
import java.util.*;

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
    public User getUser(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getUsersList() {
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userDao.findAllByRole(role);
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
            admin.setVkId(256812464);
            userDao.save(admin);
        }
    }

    public void setRole(User user, String roleValue) {

        Collection<Role> roles = new ArrayList<>();
        Role roleAdmin = new Role();
        roleAdmin.setRole("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        Role roleRequest = new Role();
        roleRequest.setRole("ROLE_REQUEST");
        if (Objects.equals(roleValue, "ROLE_ADMIN")) {
            user.setRole("ADMIN");
            roles.add(roleAdmin);
            roles.add(roleUser);
        }
        if (Objects.equals(roleValue, "ROLE_REQUEST")) {
            user.setRole("REQUEST");
            roles.add(roleRequest);
            roles.add(roleUser);
        }
        if (Objects.equals(roleValue, "ROLE_USER")){
            user.setRole("USER");
            roles.add(roleUser);
        }
        user.setRoles(roles);
    }
}
