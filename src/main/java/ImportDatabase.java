package main.java;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ImportDatabase {

    public List<UserHelper> get_data() throws IOException, InterruptedException {



        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference();
        Semaphore semaphore = new Semaphore(0);
        final List<UserHelper> users = new ArrayList<>();

        reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                UserHelper user;

                for (DataSnapshot ds : snapshot.getChildren()) {

                    user = ds.getValue(UserHelper.class);
                    System.out.println("ImportDatabase action called, status is " + user.getStatus() );

                    if (!user.getStatus().equals("Decided - Approved") && !user.getStatus().equals("Decided - Rejected")) {
                        users.add(user);
                    }

                }
                semaphore.release();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        }
);


        semaphore.acquire();
        return users;
    }
}
