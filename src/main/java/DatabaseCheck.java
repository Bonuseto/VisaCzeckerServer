package main.java;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DatabaseCheck {

    public static String checkDatabase() throws ExecutionException, InterruptedException, IOException {
        System.out.println("Here we go");
        ImportDatabase result = new ImportDatabase();
        List<UserHelper> users_database = result.get_data();
        UpdateDatabase update = new UpdateDatabase();
        String status = null;

        System.out.println(users_database);

        for (UserHelper user : users_database) {
            status = StatusCheck.check(user.getAppNum(), user.getAppNumFak(), user.getType(), user.getYear());
            System.out.println("Application number " + user.getAppNum());
            System.out.println("Current status is " + status);
            System.out.println("Status in DB is " + user.getStatus());

            update.update_data(user.getAppNum(), status);
            System.out.println(users_database);


        }
        return status;
    }
}
