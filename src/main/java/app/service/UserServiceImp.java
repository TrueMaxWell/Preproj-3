package app.service;

import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public void changeUser(User user) {
        userDao.changeUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
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
}
