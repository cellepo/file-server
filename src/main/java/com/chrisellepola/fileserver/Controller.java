package com.chrisellepola.fileserver;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class Controller {

    @GetMapping("/health-check")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void healthCheck(){

    }

    @PostMapping("/store/{fileName}")
    public ResponseEntity<Void> create(@PathVariable String fileName){
        try {
            (new File(fileName)).createNewFile();

            return new ResponseEntity(HttpStatus.OK);

        } catch(IOException ioe){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/store/{fileName}")
    public void read(@PathVariable String fileName, HttpServletResponse response) {
        try {
            final InputStream inputStream = (new ClassPathResource(fileName)).getInputStream();
            final OutputStream outputStream = response.getOutputStream();

            IOUtils.copy(inputStream, outputStream);

            inputStream.close();
            outputStream.close();

        } catch(IOException ioe){
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @DeleteMapping("/store/{fileName}")
    public ResponseEntity<Void> delete(@PathVariable String fileName){
        if(     ! fileName.isEmpty()
            &&  (new File("src/main/resources/" + fileName)).delete()){

            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/store")
    public void create() throws IOException {
        (new File("test.txt")).createNewFile();
    }

}
