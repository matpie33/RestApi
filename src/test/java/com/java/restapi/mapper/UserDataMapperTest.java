package com.java.restapi.mapper;


import com.java.restapi.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserDataMapperTest {

    @InjectMocks
    private UserDataMapper userDataMapper;

    @Test
    public void shouldMapData() {
        //given
        Map inputData = new HashMap<>();
        String avatarUrl = "test.com";
        inputData.put("avatar_url", avatarUrl);
        String creationTime = "12.02.1993";
        inputData.put("created_at", creationTime);
        String login = "abc";
        inputData.put("login", login);
        String name = "test name";
        inputData.put("name", name);
        String type = "user";
        inputData.put("type", type);
        int numberOfFollowers = 13;
        inputData.put("followers", numberOfFollowers);
        int numberOfPublicRepositories = 2;
        inputData.put("public_repos", numberOfPublicRepositories);
        inputData.put("id", 8000);

        //when
        UserDTO userDTO = userDataMapper.mapUserData(inputData);

        //then
        assertEquals(userDTO.getAvatarUrl(), avatarUrl);
        assertEquals(userDTO.getCreatedDate(), creationTime);
        assertEquals(userDTO.getLogin(), login);
        assertEquals(userDTO.getName(), name);
        assertEquals(userDTO.getType(), type);
        double expectedCalculationsValue = (double) 6 / numberOfFollowers * (numberOfPublicRepositories + 2);
        assertEquals(userDTO.getCalculations(), String.valueOf(expectedCalculationsValue));
        assertEquals(userDTO.getId(), 8000L);
    }

    @Test
    public void shouldHandleCaseWithZeroFollowers() {
        //given
        Map inputData = new HashMap<>();
        int numberOfFollowers = 0;
        inputData.put("followers", numberOfFollowers);
        inputData.put("id", 200);
        int numberOfPublicRepositories = 2;
        inputData.put("public_repos", numberOfPublicRepositories);

        //when
        UserDTO userDTO = userDataMapper.mapUserData(inputData);

        //then
        assertEquals(userDTO.getCalculations(), "n/a");
    }
}