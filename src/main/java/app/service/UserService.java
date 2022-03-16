package app.service;

import app.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService {
    void add(User user);
    void removeUser(Long id);
    void changeUser(User user);
    User getUser(Long id);
    List<User> getUsersList();
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void setRole(User user,String role);
}

