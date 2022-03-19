package app.service;

import app.repository.UserDao;
import app.model.Role;
import app.model.User;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
import com.vk.api.sdk.objects.users.Fields;
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

    VkApiClient vk = new VkApiClient(new HttpTransportClient());
    ServiceActor serviceActor = new ServiceActor(8107922,
            "f9c2e94bf9c2e94bf9c2e94b36f9b95ed9ff9c2f9c2e94b9bf7f167cd5a79b38161f781");
    GroupActor groupActor = new GroupActor(8107922,
            "61e3e0cca6a1c05e9887be3c38a7a17d946d6b4b96085a91cffa3f698134575f3abf9bfbe77d16210f2d8");

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
        return vk.users().get(serviceActor).userIds(vkId).fields(Fields.PHOTO_50, Fields.PHOTO_100).executeAsString();
    }

    @Override
    public String getVkGroupToken() {
        String clientId = "8107922";
        String redirectUri = "localhost:8080/login";
        String groupId = "211876865";
        String display = "page";
        String scope = "messages";
        String responseType = "token";
        String version = "5.131";
        String url = "https://oauth.vk.com/authorize?" +
                "client_id=" + clientId +
                "&group_ids=" + groupId +
                "&display=" + display +
                "&redirect_uri=" + redirectUri +
                "&scope=" + scope +
                "&response_type=" + responseType +
                "&v=" + version;
//        GroupAuthResponse authResponse = vk.oAuth().groupAuthorizationCodeFlow(
//                8106696, "pIo8AWcWFAVy9Ql3Hx1C", "localhost:8080","getkey").execute();
//        GroupActor groupActor = new GroupActor(211876865, authResponse.getAccessTokens().get(211876865));
        return url;
    }

    @Override
    public String getVkUserToken() {
        String clientId = "8107922";
        String redirectUri = "https://oauth.vk.com/blank.html";
        String display = "page";
        String scope = "messages";
        String responseType = "token";
        String version = "5.131";
        String url = "https://oauth.vk.com/authorize?" +
                "client_id=" + clientId +
                "&display=" + display +
                "&redirect_uri=" + redirectUri +
                "&scope=" + scope +
                "&response_type=" + responseType +
                "&v=" + version;
        return url;
    }


    @Override
    public List<User> getUsersByRole(String role) {
        return userDao.findAllByRole(role);
    }

    @Override
    public void sendAllowGrantsMessage(List<User> adminList) {
        adminList.forEach(admin -> {
            try {
                vk.messages()
                        .send(groupActor)
                        .userIds(admin.getVkId())
                        .randomId((int) (Math.random() * 100))
                        .message("Повился новый кандидат в админы!").executeAsString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
//
        }
        System.out.println(getUsersByRole("ADMIN"));
        sendAllowGrantsMessage(getUsersByRole("ADMIN"));
    }

    public void setRole(User user, String roleValue) {
        Collection<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRole("ROLE_USER");
        roles.add(role);
        if (Objects.equals(roleValue, "ROLE_ADMIN")) {
            role.setRole("ROLE_ADMIN");
            user.setRole("ADMIN");
            roles.add(role);
        } else {
            user.setRole("USER");
        }
        user.setRoles(roles);
    }
}
