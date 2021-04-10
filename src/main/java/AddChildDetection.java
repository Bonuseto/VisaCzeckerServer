package main.java;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import org.springframework.stereotype.Service;

//Running status check of recently added application
@Service
public class AddChildDetection implements ChildEventListener {

    UserHelper user = new UserHelper();

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        user = snapshot.getValue(UserHelper.class);
        String status = StatusCheck.check(user.getAppNum(), user.getAppNumFak(), user.getType(), user.getYear());
        UpdateStatus update = new UpdateStatus();
        update.update_data(user.uniqueID + " - " + user.getAppNum(), status);
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

    }

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

    }

    @Override
    public void onCancelled(DatabaseError error) {

    }
}

