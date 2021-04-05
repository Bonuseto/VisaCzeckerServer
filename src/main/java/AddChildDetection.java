package main.java;

import com.google.firebase.database.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Service
public class AddChildDetection {


    public void child_detection() throws InterruptedException {
        System.out.println("Looks like child has been added 1");
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            DatabaseReference reference = rootNode.getReference();
            Semaphore semaphore = new Semaphore(0);
            final List<UserHelper> users = new ArrayList<>();

            reference.child("users").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    UserHelper user;
                    String status = null;
                    user = dataSnapshot.getValue(UserHelper.class);
                    status = StatusCheck.check(user.getAppNum(), user.getAppNumFak(), user.getType(), user.getYear());
                    System.out.println("Looks like child has been added" + status);



                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s)
                {

                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

           /*     @Override
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


            }*/



            semaphore.acquire();

        }

    }

