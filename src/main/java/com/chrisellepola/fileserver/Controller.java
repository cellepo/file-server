package com.chrisellepola.fileserver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class Controller {

    @GetMapping("/health-check")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void healthCheck(){

    }

    @GetMapping("/store")
    public StreamingResponseBody read() throws IOException {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream out) throws IOException {
                for (int i = 0; i < 1000; i++) {
                    out.write((i + " - ").getBytes());
                    out.flush();
                    try {
                        Thread.sleep(5);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @PostMapping("/store")
    public void create() throws IOException {
        (new File("test.txt")).createNewFile();
    }

}
