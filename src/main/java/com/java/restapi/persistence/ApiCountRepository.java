package com.java.restapi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiCountRepository extends JpaRepository<ApiCountForLogin, Long> {

    ApiCountForLogin findByLogin(String login);

}
