package app.vk;

import app.model.User;
import app.service.UserService;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VkService {

    @Autowired
    UserService userService;

    VkApiClient vk = new VkApiClient(new HttpTransportClient());
    ServiceActor serviceActor = new ServiceActor(8107922,
            "f9c2e94bf9c2e94bf9c2e94b36f9b95ed9ff9c2f9c2e94b9bf7f167cd5a79b38161f781");
    GroupActor groupActor = new GroupActor(8107922,
            "61e3e0cca6a1c05e9887be3c38a7a17d946d6b4b96085a91cffa3f698134575f3abf9bfbe77d16210f2d8");

    public String getVkInfo(String vkId) throws ClientException {
        return vk.users().get(serviceActor).userIds(vkId).fields(Fields.PHOTO_50, Fields.PHOTO_100).executeAsString();
    }

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
        return url;
    }

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


    public void sendAllowGrantsMessage(String name) {
        List<User> adminList = userService.getUsersByRole("ADMIN");
        adminList.forEach(admin -> {
            try {
                vk.messages()
                        .send(groupActor)
                        .userIds(admin.getVkId())
                        .randomId((int) (Math.random() * 10000))
                        .message("Повился новый кандидат в админы!\n" + name).executeAsString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
