package main.java;

import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {
    public FirebaseService() {
        AddChildDetection addChildDetection = new AddChildDetection();
        FirebaseDatabase.getInstance().getReference().child("users").orderByChild("firstTimeAdded").equalTo("true").addChildEventListener(addChildDetection);
    }
}
