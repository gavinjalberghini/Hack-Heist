package edu.vcu.mymail.alberghinig.hackheist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Leaderboard extends AppCompatActivity {

    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        ImageButton backButton = findViewById(R.id.Leaderboard_BackButton);

        TextView rankBox1 = findViewById(R.id.Leaderboard_Rank1TextView);
        TextView rankBox2 = findViewById(R.id.Leaderboard_Rank2TextView);
        TextView rankBox3 = findViewById(R.id.Leaderboard_Rank3TextView);
        TextView rankBox4 = findViewById(R.id.Leaderboard_Rank4TextView);
        TextView rankBox5 = findViewById(R.id.Leaderboard_Rank5TextView);
        //TextView rankBoxUser = findViewById(R.id.Leaderboard_RankUserTextView);



        TextView userBox1 = findViewById(R.id.Leaderboard_Username1TextView);
        TextView userBox2 = findViewById(R.id.Leaderboard_Username2TextView);
        TextView userBox3 = findViewById(R.id.Leaderboard_Username3TextView);
        TextView userBox4 = findViewById(R.id.Leaderboard_Username4TextView);
        TextView userBox5 = findViewById(R.id.Leaderboard_Username5TextView);
        //TextView userBoxUser = findViewById(R.id.Leaderboard_UsernameUserTextView);


        TextView scoreBox1 = findViewById(R.id.Leaderboard_Score1TextView);
        TextView scoreBox2 = findViewById(R.id.Leaderboard_Score2TextView);
        TextView scoreBox3 = findViewById(R.id.Leaderboard_Score3TextView);
        TextView scoreBox4 = findViewById(R.id.Leaderboard_Score4TextView);
        TextView scoreBox5 = findViewById(R.id.Leaderboard_Score5TextView);
        //TextView scoreBoxUser = findViewById(R.id.Leaderboard_ScoreUserTextView);


        ArrayList<User> leaderboard = controller.getListOfUsers();

        Collections.sort(leaderboard, new Comparator<User>()  {
            @Override
            public int compare(User o1, User o2) {
                String userName1 = o1.getUsername();
                String userName2 = o2.getUsername();
                return userName1.compareTo(userName2);
            }
        });

        for(int i=0; i<=5; i++){

            if(leaderboard.size() > i){
                switch(i){

                    case 0:
                        rankBox1.setText("Rank " + (i+1));
                        userBox1.setText(leaderboard.get(i).getUsername());
                        scoreBox1.setText("000");
                        break;
                    case 1:
                        if(0 == leaderboard.get(i-1).getUsername().compareTo(leaderboard.get(i).getUsername()))
                            rankBox2.setText(rankBox1.getText().toString());
                        else {
                            String nextRank = rankBox2.getText().toString();
                            nextRank = nextRank.substring(4);
                            nextRank = nextRank.trim();
                            int nextRankInt = Integer.parseInt(nextRank);
                            nextRankInt ++;
                            nextRank = "Rank " + nextRankInt;
                            rankBox3.setText(nextRank);
                        }
                        userBox2.setText(leaderboard.get(i).getUsername());
                        scoreBox2.setText("000");
                        break;
                    case 2:
                        if(0 == leaderboard.get(i-1).getUsername().compareTo(leaderboard.get(i).getUsername()))
                            rankBox3.setText(rankBox2.getText().toString());
                        else {
                            String nextRank = rankBox2.getText().toString();
                            nextRank = nextRank.substring(4);
                            nextRank = nextRank.trim();
                            int nextRankInt = Integer.parseInt(nextRank);
                            nextRankInt ++;
                            nextRank = "Rank " + nextRankInt;
                            rankBox3.setText(nextRank);
                        }
                        userBox3.setText(leaderboard.get(i).getUsername());
                        scoreBox3.setText("000");
                        break;
                    case 3:
                        if(0 == leaderboard.get(i-1).getUsername().compareTo(leaderboard.get(i).getUsername()))
                            rankBox4.setText(rankBox3.getText().toString());
                        else {
                            String nextRank = rankBox3.getText().toString();
                            nextRank = nextRank.substring(4);
                            nextRank = nextRank.trim();
                            int nextRankInt = Integer.parseInt(nextRank);
                            nextRankInt ++;
                            nextRank = "Rank " + nextRankInt;
                            rankBox4.setText(nextRank);
                        }
                        userBox4.setText(leaderboard.get(i).getUsername());
                        scoreBox4.setText("000");
                        break;
                    case 4:
                        if(0 == leaderboard.get(i-1).getUsername().compareTo(leaderboard.get(i).getUsername()))
                            rankBox5.setText(rankBox4.getText().toString());
                        else {
                            String nextRank = rankBox4.getText().toString();
                            nextRank = nextRank.substring(4);
                            nextRank = nextRank.trim();
                            int nextRankInt = Integer.parseInt(nextRank);
                            nextRankInt ++;
                            nextRank = "Rank " + nextRankInt;
                            rankBox5.setText(nextRank);
                        }
                        userBox5.setText(leaderboard.get(i).getUsername());
                        scoreBox5.setText("000");
                        break;
                }
            }
        }


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