package com.example.tusher.cityppolish;

import com.example.tusher.cityppolish.helper.SQLiteHandler;
import com.example.tusher.cityppolish.helper.SessionManager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardScreen extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button btnsendorupload;
    private Button btnnewsfeed;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        btnLogout = (Button) findViewById(R.id.btnLogout); // reference to the logout button on UI
        btnnewsfeed = (Button) findViewById(R.id.btnnewsfeed); //reference to the newsfeed button on UI
        btnsendorupload = (Button) findViewById(R.id.btnsendorupload); // reference to the send/upload button on UI




        btnnewsfeed.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(DashboardScreen.this,newsfeed.class); // intent referring to newsfeed class
                startActivity(i); // intent i starts
                finish(); // current activity ends
            }
        });


        btnsendorupload.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(DashboardScreen.this,report.class);// intent referring to report class
                startActivity(i);// intent begins
                finish();// current intent ends
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(DashboardScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
