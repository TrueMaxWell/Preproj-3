package app.json;

import app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    public List<User> getUsers(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> result = new ArrayList<>();
        try {
            result = Arrays.asList(mapper.readValue(json, User[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
