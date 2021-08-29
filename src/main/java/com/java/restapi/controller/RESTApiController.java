package com.java.restapi.controller;

import com.java.restapi.dto.UserDTO;
import com.java.restapi.persistence.ApiCountPersister;
import com.java.restapi.restcall.RestCallHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTApiController {

    @Autowired
    private RestCallHandler restCallHandler;

    @Autowired
    private ApiCountPersister apiCountPersister;

    @GetMapping("/users/{login}")
    public UserDTO getDataForLogin(@PathVariable String login) {
        UserDTO userDTO = restCallHandler.makeCallForLogin(login);
        apiCountPersister.persistApiCount(login);
        return userDTO;
    }


}
