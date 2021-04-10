package main.java;

import com.google.firebase.database.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ImportDatabase {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference = rootNode.getReference();
    Semaphore semaphore = new Semaphore(0);
    final List<UserHelper> users = new ArrayList<>();

    //Importing all database objects that don't have final status "Decided - Rejected" or "Decided - Approved"
    public List<UserHelper> get_data() throws IOException, InterruptedException {


        reference.child("users").orderByChild("finalStatus").equalTo("false").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                UserHelper user;

                for (DataSnapshot ds : snapshot.getChildren()) {
                    user = ds.getValue(UserHelper.class);
                    users.add(user);
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
