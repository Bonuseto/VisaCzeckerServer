package main.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
@SpringBootApplication
@EnableScheduling

public class Main {



    public static void main(String[] args) throws Exception {
           Firebase.initialize();
             SpringApplication.run(Main.class, args);
       // DatabaseCheck.checkDatabase();
       // String status = StatusCheck.check("5194", "4", "TP", "2021");
       // System.out.println(status);
    }


}




