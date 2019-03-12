package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ImageButton backButton = findViewById(R.id.Leaderboard_BackButton);

        TableLayout leaderboardTable = (TableLayout)findViewById(R.id.Leaderboard_LeaderboardTable);
        leaderboardTable.setStretchAllColumns(true);
        leaderboardTable.bringToFront();

//        for(int i = 0; i < 10; i++){
//            TableRow tr =  new TableRow(this);

//            TextView c1 = new TextView(this);
//            c1.setText(Username);
//
//            TextView c2 = new TextView(this);
//            c2.setText(Badges Earned);
//
//            TextView c3 = new TextView(this);
//            c3.setText(Keycards Earned);
//
//            tr.addView(c1);
//            tr.addView(c2);
//            tr.addView(c3);

//            leaderboardTable.addView(tr);
//        }

        View.OnClickListener goBackEvent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(I);
            }
        };

        backButton.setOnClickListener(goBackEvent);
    }
}
