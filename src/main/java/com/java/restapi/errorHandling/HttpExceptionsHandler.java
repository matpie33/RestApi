package com.java.restapi.errorHandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public class HttpExceptionsHandler {

    private static final Logger LOGGER = LogManager.getLogger(HttpExceptionsHandler.class);

    @ResponseBody
    @ExceptionHandler({RestClientResponseException.class})
    String restClientResponseExceptionHandler(RestClientResponseException ex) {
        LOGGER.debug(ex);
        return "Invalid data was passed.";
    }

    @ResponseBody
    @ExceptionHandler({NoDataReturnedException.class})
    String noDataReturnedExceptionHandler(NoDataReturnedException ex) {
        LOGGER.debug(ex);
        return "No data was returned for given user.";
    }

}
