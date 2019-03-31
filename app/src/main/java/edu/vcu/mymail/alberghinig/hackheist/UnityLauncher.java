package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.IT.HH.UnityPlayerActivity;

public class UnityLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_launcher);
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        startActivity(intent);
    }
}
