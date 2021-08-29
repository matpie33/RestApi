package com.java.restapi.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiCountPersister {

    @Autowired
    private ApiCountRepository apiCountRepository;

    public void persistApiCount(String login) {
        ApiCountForLogin apiCount = apiCountRepository.findByLogin(login);
        if (apiCount == null) {
            apiCount = new ApiCountForLogin();
            apiCount.setLogin(login);
            apiCount.setRequestCount(0L);
        }
        apiCount.setRequestCount(apiCount.getRequestCount() + 1);
        apiCountRepository.save(apiCount);
    }

}
