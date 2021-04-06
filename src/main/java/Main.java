package main.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class Main {


    public static void main(String[] args) throws Exception {
        Firebase.initialize();
        SpringApplication.run(Main.class, args);
    }


}




