package main.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    @Autowired
    private DatabaseCheck databaseCheck;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 100000000)
    public void reportCurrentTime() throws InterruptedException, ExecutionException, IOException {
        //log.info("The time is now {}", dateFormat.format(new Date()));
        log.info("The time is now {}", databaseCheck.checkDatabase());
    }
}
