package main.java;

import com.google.api.core.ApiFuture;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UpdateDatabase {

    ApiFuture<Void> future;
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    final Map<String, Object> users = new HashMap<>();

    public void update_data(String address, String status) throws ExecutionException, InterruptedException {
        users.put("status", status);
        reference = rootNode.getReference("users/" + address);
        future = reference.updateChildrenAsync(users);
        future.get();


    }
}
