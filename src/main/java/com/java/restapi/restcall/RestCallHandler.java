package com.java.restapi.restcall;

import com.java.restapi.dto.UserDTO;
import com.java.restapi.errorHandling.NoDataReturnedException;
import com.java.restapi.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class RestCallHandler {

    public static final String API_URL_TEMPLATE = "https://api.github.com/users/{login}";
    private RestTemplate restTemplate;

    @Autowired
    private UserDataMapper userDataMapper;

    @PostConstruct
    public void initialize() {
        restTemplate = new RestTemplate();
    }

    public UserDTO makeCallForLogin(String login) {

        Map dataFromGithub = restTemplate.getForObject(API_URL_TEMPLATE, Map.class, login);
        if (dataFromGithub != null) {
            return userDataMapper.mapUserData(dataFromGithub);
        } else {
            throw new NoDataReturnedException("No data was returned for login: " + login);
        }
    }

}
