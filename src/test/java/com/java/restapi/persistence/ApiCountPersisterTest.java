package com.java.restapi.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiCountPersisterTest {

    @InjectMocks
    private ApiCountPersister apiCountPersister;

    @Mock
    private ApiCountRepository apiCountRepository;

    @Test
    public void shouldPersistApiCountForFirstTime() {
        //given
        String login = "abc";
        when(apiCountRepository.findByLogin(login)).thenReturn(null);

        //when
        apiCountPersister.persistApiCount(login);

        //then
        ArgumentCaptor<ApiCountForLogin> captorForApiCount = ArgumentCaptor.forClass(ApiCountForLogin.class);
        verify(apiCountRepository).save(captorForApiCount.capture());
        assertEquals(captorForApiCount.getAllValues().size(), 1);
        ApiCountForLogin capturedValue = captorForApiCount.getValue();
        assertEquals(capturedValue.getLogin(), login);
        assertEquals(capturedValue.getRequestCount(), 1);

    }

    @Test
    public void shouldPersistApiCountAndIncrementCounter() {
        //given
        String login = "abc";
        ApiCountForLogin apiCountAfterFirstSave = new ApiCountForLogin();
        apiCountAfterFirstSave.setLogin(login);
        apiCountAfterFirstSave.setRequestCount(1L);
        ApiCountForLogin apiCountAfterSecondSave = new ApiCountForLogin();
        apiCountAfterSecondSave.setLogin(login);
        apiCountAfterSecondSave.setRequestCount(2L);
        when(apiCountRepository.findByLogin(login)).thenReturn(null).thenReturn(apiCountAfterFirstSave).thenReturn(apiCountAfterSecondSave);

        //when
        apiCountPersister.persistApiCount(login);
        apiCountPersister.persistApiCount(login);
        apiCountPersister.persistApiCount(login);

        //then
        ArgumentCaptor<ApiCountForLogin> captorForApiCount = ArgumentCaptor.forClass(ApiCountForLogin.class);
        verify(apiCountRepository, Mockito.times(3)).save(captorForApiCount.capture());
        List<ApiCountForLogin> capturedValues = captorForApiCount.getAllValues();
        ApiCountForLogin firstSavedValue = capturedValues.get(0);
        assertEquals(firstSavedValue.getLogin(), login);
        assertEquals(firstSavedValue.getRequestCount(), 1);
        ApiCountForLogin secondSavedValue = capturedValues.get(1);
        assertEquals(secondSavedValue.getLogin(), login);
        assertEquals(secondSavedValue.getRequestCount(), 2);
        ApiCountForLogin thirdSavedValue = capturedValues.get(2);
        assertEquals(thirdSavedValue.getLogin(), login);
        assertEquals(thirdSavedValue.getRequestCount(), 3);

    }

    @Test
    public void shouldPersistApiCountForDifferentLogins() {
        //given
        String login1 = "abc";
        String login2 = "cba";
        ApiCountForLogin apiCountLogin2AfterFirstSave = new ApiCountForLogin();
        apiCountLogin2AfterFirstSave.setLogin(login2);
        apiCountLogin2AfterFirstSave.setRequestCount(1L);
        when(apiCountRepository.findByLogin(login1)).thenReturn(null);
        when(apiCountRepository.findByLogin(login2)).thenReturn(null).thenReturn(apiCountLogin2AfterFirstSave);

        //when
        apiCountPersister.persistApiCount(login1);
        apiCountPersister.persistApiCount(login2);
        apiCountPersister.persistApiCount(login2);

        //then
        ArgumentCaptor<ApiCountForLogin> captorForApiCount = ArgumentCaptor.forClass(ApiCountForLogin.class);
        verify(apiCountRepository, Mockito.times(3)).save(captorForApiCount.capture());
        List<ApiCountForLogin> capturedValues = captorForApiCount.getAllValues();
        ApiCountForLogin firstSavedValue = capturedValues.get(0);
        assertEquals(firstSavedValue.getLogin(), login1);
        assertEquals(firstSavedValue.getRequestCount(), 1);
        ApiCountForLogin secondSavedValue = capturedValues.get(1);
        assertEquals(secondSavedValue.getLogin(), login2);
        assertEquals(secondSavedValue.getRequestCount(), 1);
        ApiCountForLogin thirdSavedValue = capturedValues.get(2);
        assertEquals(thirdSavedValue.getLogin(), login2);
        assertEquals(thirdSavedValue.getRequestCount(), 2);

    }
}