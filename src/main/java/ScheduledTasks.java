package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    @Autowired
    private DatabaseCheck databaseCheck;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //Scheduling run of checking entire database
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws InterruptedException, ExecutionException, IOException {
        log.info("It's time to check database {}", databaseCheck.checkDatabase());
    }
}
