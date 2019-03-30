package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    private static String TAG = "MainMenu";
    private Handler handler;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                Intent logout = new Intent(getApplicationContext(), Welcome.class);
                startActivity(logout);
                Log.d(TAG, "Logged out after 5 minutes of inactivity.");
                finish();

                Toast.makeText(MainMenu.this, "Logged out after 5 minutes of inactivity.", Toast.LENGTH_SHORT).show();
            }
        };

        startHandler();

        ActiveUser user = new ActiveUser(false);
        Log.d("LOGGED IN USER FROM MM", user.toString());

        Button campaignButton = findViewById(R.id.MainMenu_CampaignButton);
        Button leaderBoardButton = findViewById(R.id.MainMenu_LeaderboardButton);
        Button resetDataButton = findViewById(R.id.MainMenu_ResetButton);
        Button helpButton = findViewById(R.id.MainMenu_HelpScreenButton);
        Button userSettingsButton = findViewById(R.id.MainMenu_UserSettingsButton);
        Button logoutButton = findViewById(R.id.MainMenu_LogoutButton);


        View.OnClickListener campaignEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UnityLauncher.class);
                startActivity(I);
            }
        };

        View.OnClickListener leaderboardEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Leaderboard.class);
                startActivity(I);
            }
        };

        View.OnClickListener resetDataEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement a reset data page with password verification
            }
        };

        View.OnClickListener helpEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Help.class);
                startActivity(I);
            }
        };

        View.OnClickListener userSettingsEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), UserSettings.class);
                startActivity(I);
            }
        };

        View.OnClickListener logoutEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), Welcome.class);
                startActivity(I);
            }
        };

        campaignButton.setOnClickListener(campaignEvent);
        leaderBoardButton.setOnClickListener(leaderboardEvent);
        resetDataButton.setOnClickListener(resetDataEvent);
        helpButton.setOnClickListener(helpEvent);
        userSettingsButton.setOnClickListener(userSettingsEvent);
        logoutButton.setOnClickListener(logoutEvent);

    }

    public void stopHandler() {
        handler.removeCallbacks(runnable);
        Log.d("HandlerRun", "stopHandlerMain");
    }

    public void startHandler() {
        handler.postDelayed(runnable, 5 * 60 * 1000);
        Log.d("HandlerRun", "startHandlerMain");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        stopHandler();
        startHandler();
    }

    @Override
    protected void onPause() {
        stopHandler();
        Log.d("onPause", "onPauseActivity change");
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startHandler();
        Log.d("onResume", "onResume_restartActivity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopHandler();
        Log.d("onDestroy", "onDestroyActivity change");

    }


}
