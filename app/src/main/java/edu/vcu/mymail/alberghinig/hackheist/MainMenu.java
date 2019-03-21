package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button campaignButton = findViewById(R.id.MainMenu_CampaignButton);
        Button leaderBoardButton = findViewById(R.id.MainMenu_LeaderboardButton);
        Button resetDataButton = findViewById(R.id.MainMenu_ResetButton);
        Button helpButton = findViewById(R.id.MainMenu_HelpScreenButton);
        Button userSettingsButton = findViewById(R.id.MainMenu_UserSettingsButton);
        Button logoutButton = findViewById(R.id.MainMenu_LogoutButton);


        View.OnClickListener campaignEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Will launch the game from this button
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
}
