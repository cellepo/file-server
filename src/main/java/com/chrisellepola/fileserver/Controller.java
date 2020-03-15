package com.chrisellepola.fileserver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/health-check")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void healthCheck(){

    }

}
