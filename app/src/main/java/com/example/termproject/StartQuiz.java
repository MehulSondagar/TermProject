package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StartQuiz extends AppCompatActivity {

    TextView result,txtQues;
    ListView lst1;
    Button submit;
    RadioGroup radioGroup;
    RadioButton radioButton;

    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        result = (TextView)findViewById(R.id.txtResult);
        lst1 = findViewById(R.id.lst1);
        txtQues = findViewById(R.id.txtQues1);
        submit = findViewById(R.id.btnSubmit);
        radioGroup = findViewById(R.id.radioGroup);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from Quiz",null);
        int id = c.getColumnIndex("id");
        int question = c.getColumnIndex("question");
        int answer = c.getColumnIndex("answer");

        titles.clear();


        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<Quiz> stud = new ArrayList<Quiz>();


        if(c.moveToFirst())
        {
            do{
                Quiz stu = new Quiz();
                stu.id = c.getString(id);
                stu.question = c.getString(question);
                stu.answer = c.getString(answer);

                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(question) + " \t "  + c.getString(answer) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                Quiz stu = stud.get(position);
                String ans1 = "";
                txtQues.setText(stu.question);
                ans1 = stu.answer;
                result.setText(ans1);
                /*Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("id",stu.id);
                i.putExtra("question",stu.question);
                i.putExtra("answer",stu.answer);
                startActivity(i);

                 */

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedID);
                if (txtQues.getText().toString().equals("")) {
                    Toast.makeText(StartQuiz.this, "Select your Question", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StartQuiz.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                    if (radioButton.getText().equals(result.getText())) {
                        result.setText("Your answer is True!");
                    } else {
                        result.setText("Your Answer is Wrong");
                    }
                }
            }
        });

    }
}