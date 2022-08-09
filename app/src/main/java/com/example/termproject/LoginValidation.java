package com.example.termproject;

import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginValidation extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    TextView username;
    TextView password;
    public static final String mypreference = "mypref";
    public static final String UserName = "nameKey";
    public static final String Password = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_validation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        username = (TextView) findViewById(R.id.txtUsername);
        password = (TextView) findViewById(R.id.etPassword);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(UserName)) {
            username.setText(sharedpreferences.getString(UserName, ""));
        }
        if (sharedpreferences.contains(Password)) {
            password.setText(sharedpreferences.getString(Password, ""));
        }

    }

    public void Login(View view) {
        String n = username.getText().toString();
        String p = password.getText().toString();
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        //get data from shared pref
        String un = sharedpreferences.getString(UserName, "");
        String em = sharedpreferences.getString(Password, "");


        if (n.equals(un) && p.equals(em)){
            Toast.makeText(this, "User and email is correct " + un + "," + em, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "User and email is wrong " +  un + "," + em, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}