package edu.vcu.mymail.alberghinig.hackheist;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard extends AppCompatActivity {

    DBController controller = new DBController(this);
    ProgressDialog prgDialog;

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

        TextView userBox1 = findViewById(R.id.Leaderboard_Username1TextView);
        TextView userBox2 = findViewById(R.id.Leaderboard_Username2TextView);
        TextView userBox3 = findViewById(R.id.Leaderboard_Username3TextView);
        TextView userBox4 = findViewById(R.id.Leaderboard_Username4TextView);
        TextView userBox5 = findViewById(R.id.Leaderboard_Username5TextView);

        TextView scoreBox1 = findViewById(R.id.Leaderboard_Score1TextView);
        TextView scoreBox2 = findViewById(R.id.Leaderboard_Score2TextView);
        TextView scoreBox3 = findViewById(R.id.Leaderboard_Score3TextView);
        TextView scoreBox4 = findViewById(R.id.Leaderboard_Score4TextView);
        TextView scoreBox5 = findViewById(R.id.Leaderboard_Score5TextView);



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