package com.java.restapi.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiCountForLogin {

    @Id
    private String login;

    private Long requestCount;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

}
