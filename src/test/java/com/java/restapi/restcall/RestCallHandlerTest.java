package com.java.restapi.restcall;

import com.java.restapi.dto.UserDTO;
import com.java.restapi.errorHandling.NoDataReturnedException;
import com.java.restapi.mapper.UserDataMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestCallHandlerTest {

    @InjectMocks
    private RestCallHandler restCallHandler;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserDataMapper userDataMapper;

    @SuppressWarnings("rawtypes")
    @Test
    public void shouldCallMapUserDataIfRestReturnedData() {
        //given
        String login = "abc";
        HashMap inputData = new HashMap();
        when(restTemplate.getForObject("https://api.github.com/users/{login}", Map.class, login)).thenReturn(inputData);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2000L);
        when(userDataMapper.mapUserData(inputData)).thenReturn(userDTO);

        //when
        UserDTO returnedUser = restCallHandler.makeCallForLogin(login);

        //then
        Assertions.assertEquals(returnedUser, userDTO);
    }

    @Test
    public void shouldThrowExceptionIfRestReturnedNoData() {

        //given
        String login = "abc";
        when(restTemplate.getForObject("https://api.github.com/users/{login}", Map.class, login)).thenReturn(null);

        //when, then
        Assertions.assertThrows(NoDataReturnedException.class, () -> restCallHandler.makeCallForLogin(login), "No data was returned for login: " + login);
    }
}