package main.java;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

//Checking status of all applications in database
@Service
public class DatabaseCheck {

    public static String checkDatabase() throws InterruptedException, IOException {

        ImportDatabase result = new ImportDatabase();
        List<UserHelper> users_database = result.get_data();
        UpdateStatus update = new UpdateStatus();
        String status = null;

        System.out.println(users_database);

        for (UserHelper user : users_database) {
            status = StatusCheck.check(user.getAppNum(), user.getAppNumFak(), user.getType(), user.getYear());
            update.update_data(user.uniqueID + " - " + user.getAppNum(), status);
        }
        return status;
    }
}
