package com.hk3t.inote;

import com.hk3t.inote.service.NoteService;
import com.hk3t.inote.service.NoteTypeService;
import com.hk3t.inote.service.impl.NoteServiceImpl;
import com.hk3t.inote.service.impl.NoteTypeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(InoteApplication.class, args);
    }

    @Bean
    public NoteService noteService() {
        return new NoteServiceImpl();
    }

    @Bean
    public NoteTypeService noteTypeService() {
        return new NoteTypeServiceImpl();
    }
}

