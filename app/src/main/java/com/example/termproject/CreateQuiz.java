package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    EditText ques,ans;
    Button btnAdd,btnDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        ques = (EditText)findViewById(R.id.txtQuestion);
        ans = (EditText) findViewById(R.id.txtAnswer);

        btnAdd = findViewById(R.id.btnAdd);
        btnDone = findViewById(R.id.btnDone);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
    public void insert()
    {
        try
        {
            String question = ques.getText().toString();
            String answer = ans.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Quiz(id INTEGER PRIMARY KEY AUTOINCREMENT,question VARCHAR,answer VARCHAR)");

            String sql = "insert into Quiz(question,answer)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,question);
            statement.bindString(2,answer);

            statement.execute();
            Toast.makeText(this,"Record added to Question table. SUCCESS!",Toast.LENGTH_LONG).show();

            ques.setText("");
            ans.setText("");

            ques.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail*",Toast.LENGTH_LONG).show();
        }
    }
}