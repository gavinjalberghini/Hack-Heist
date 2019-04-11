package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.IT.HH.UnityPlayerActivity;

public class UnityLauncher extends com.unity3d.player.UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_launcher);
        ActiveUser user = new ActiveUser(false);

        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("arguments", user.getUsername());
        startActivity(intent);
    }

}
