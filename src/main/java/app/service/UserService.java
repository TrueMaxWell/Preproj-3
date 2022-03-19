package app.service;

import app.model.User;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
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
    String getVkInfo(String vkId) throws ClientException;
    void setRole(User user,String role);
    String getVkGroupToken();
    List<User> getUsersByRole(String role);
    void sendAllowGrantsMessage(List<User> adminList);
    String getVkUserToken();
}

