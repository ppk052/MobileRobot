package com.example.mobilerobot;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

public class MainActivity extends AppCompatActivity /*implements
        OnRobotReadyListener,
        View.OnClickListener */
{
    Robot robot;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                System.out.println("Success to read value : " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read value : " + error.toException());
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }



//    @Override
//    public void onRobotReady(boolean isReady){
//
//    }
//
//    @Override
//    public void onClick(View view){
//
//    }

    //메서드
    //temiSDK 문서 : https://github.com/robotemi/sdk/wiki
    public void temiMethod() {

        // 사람 따라다니게하는 명령어
        robot.beWithMe();

        // 모든 움직임을 멈추는 명령어
        robot.stopMovement();

        //테미가 해당 글을 읽는 명령어
        //create의 두번째 인자는 말할때 대화창을 표시하는지의 여부
        String speak = "";
        TtsRequest ttsRequest = TtsRequest.create(speak,true, TtsRequest.Language.KO_KR);
        robot.speak(ttsRequest);

    }
}