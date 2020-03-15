package com.chrisellepola.fileserver;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class Controller {

    @GetMapping("/health-check")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void healthCheck(){

    }

    @GetMapping("/store")
    public void read(HttpServletResponse response) throws IOException {
        IOUtils.copy(new FileInputStream(new File("pic.jpg")), response.getOutputStream());
        for(int i = 0; i < 10; i++){
            IOUtils.copy(new FileInputStream(new File("Photo.jpeg")), response.getOutputStream());
        }
    }

    @PostMapping("/store")
    public void create() throws IOException {
        (new File("test.txt")).createNewFile();
    }

}
