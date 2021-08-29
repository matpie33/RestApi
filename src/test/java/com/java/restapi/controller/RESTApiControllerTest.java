package com.java.restapi.controller;

import com.java.restapi.dto.UserDTO;
import com.java.restapi.persistence.ApiCountPersister;
import com.java.restapi.restcall.RestCallHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RESTApiControllerTest {
    @InjectMocks
    private RESTApiController restApiController;

    @Mock
    private RestCallHandler restCallHandler;

    @Mock
    private ApiCountPersister apiCountPersister;

    @Test
    public void shouldGetDataForLogin() {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12L);
        String login = "abc";
        when(restCallHandler.makeCallForLogin(login)).thenReturn(userDTO);

        //when
        UserDTO returnedUser = restApiController.getDataForLogin(login);

        //then
        assertEquals(returnedUser, userDTO);
        verify(apiCountPersister).persistApiCount(login);
    }

}
