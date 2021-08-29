package com.java.restapi.mapper;

import com.java.restapi.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataMapper {

    public UserDTO mapUserData(Map inputData) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAvatarUrl((String) inputData.get("avatar_url"));
        userDTO.setCreatedDate((String) inputData.get("created_at"));
        userDTO.setLogin((String) inputData.get("login"));
        userDTO.setName((String) inputData.get("name"));
        userDTO.setType((String) inputData.get("type"));
        userDTO.setCalculations(calculateValueForCalculations((Integer) inputData.get("followers"), (Integer) inputData.get("public_repos")));
        userDTO.setId(Long.valueOf((Integer) inputData.get("id")));
        return userDTO;
    }


    private String calculateValueForCalculations(Integer followers, Integer publicRepos) {
        return followers == 0 ? "n/a" : String.valueOf((double) 6 / followers * (2 + publicRepos));
    }


}
