package com.ante.grup.korona;

import com.ante.grup.korona.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KoronaApplication implements CommandLineRunner {
    @Autowired
    private DataRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(KoronaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();

    }
}
