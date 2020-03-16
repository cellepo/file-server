package com.chrisellepola.fileserver;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class Controller {

    @GetMapping("/health-check")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void healthCheck(){

    }

    @GetMapping("/store")
    public void read(HttpServletResponse response) throws IOException {
        final InputStream inputStream = (new ClassPathResource("pic.jpg")).getInputStream();
        final OutputStream outputStream = response.getOutputStream();

        IOUtils.copy(inputStream, outputStream);

        inputStream.close();
        outputStream.close();
    }

    @PostMapping("/store")
    public void create() throws IOException {
        (new File("test.txt")).createNewFile();
    }

}
