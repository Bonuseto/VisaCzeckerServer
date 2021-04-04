package main.java;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ImportDatabase result = new ImportDatabase();
        List<UserHelper> users_database = result.get_data();
        UpdateDatabase update = new UpdateDatabase();
        String status;

        System.out.println(users_database);

        for (UserHelper user : users_database) {
            status = StatusCheck.check(user.getAppNum(), user.getAppNumFak(), user.getType(), user.getYear());
            System.out.println("Application number " + user.getAppNum());
            System.out.println("Current status is " + status);
            System.out.println("Status in DB is " + user.getStatus());

            update.update_data(user.getAppNum(), status);
            System.out.println(users_database);
        }
    }
}




