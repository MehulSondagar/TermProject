package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button createBtn;
    Button startBtn;
    TextView welcome;
    SharedPreferences sharedpreferences;

    public static final String mypreference = "mypref";
    public static final String UserName = "nameKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = (TextView) findViewById(R.id.tvWelcome);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(UserName)) {
            welcome.setText("Welcome : " + sharedpreferences.getString(UserName, ""));
        }
        createBtn = (Button) findViewById(R.id.btnCreate);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CreateQuiz.class);
                startActivity(intent);
            }
        });

        startBtn = (Button) findViewById(R.id.btnStart);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),StartQuiz.class);
                startActivity(intent);
            }
        });
    }
}