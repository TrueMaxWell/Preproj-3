package app.service;

import app.repository.UserDao;
import app.model.Role;
import app.model.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
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

    VkApiClient vk = new VkApiClient(new HttpTransportClient());
    ServiceActor actor = new ServiceActor(	8106696,
            "2f3cd5ce2f3cd5ce2f3cd5ce7a2f47670622f3c2f3cd5ce4d0f045e47a7ff36bfc6ff06");

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
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email);
    }

    @Override
    public String getVkInfo(String vkId) throws ClientException {
        return vk.users().get(actor).userIds(vkId).fields(Fields.PHOTO_50).executeAsString();
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
            admin.setVkId("maxwell25");
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
