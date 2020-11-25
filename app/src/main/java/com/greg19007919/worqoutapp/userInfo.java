package com.greg19007919.worqoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class userInfo extends AppCompatActivity {

    TextView fullname,email,height,sex;
    EditText weightGoal,calorieGoal;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        fullname = findViewById(R.id.fullNameText);
        email = findViewById(R.id.emailAdressText);
        height = findViewById(R.id.heightText);
        sex = findViewById(R.id.sexText);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        weightGoal = findViewById(R.id.weightGoalField);
        calorieGoal = findViewById(R.id.calorieGoal);

        final DocumentReference documentReference = fStore.collection( "users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fullname.setText(documentSnapshot.getString("fullName"));
                email.setText(documentSnapshot.getString("email"));
                height.setText(documentSnapshot.getString("height"));
                sex.setText(documentSnapshot.getString("sex"));
            }
        });


    }
}