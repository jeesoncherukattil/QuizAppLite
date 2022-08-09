package com.lambton.QuizAppLite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lambton.QuizAppLite.data.DbHelper;

public class CreateActivity extends AppCompatActivity {

    EditText question, option1, option2, option3, answer;
    Button createquiz;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        question = (EditText) findViewById(R.id.question);
        option1 = (EditText) findViewById(R.id.option1);
        option2 = (EditText) findViewById(R.id.option2);
        option3 = (EditText) findViewById(R.id.option3);
        answer = (EditText) findViewById(R.id.answer);

        createquiz = (Button) findViewById(R.id.createquiz);
         DB = new DbHelper(this);
        createquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String quest = question.getText().toString();
                String opt1 = option1.getText().toString();
                String opt2 = option2.getText().toString();
                String opt3 = option3.getText().toString();
                String ans = answer.getText().toString();

                if(quest.equals("")||opt1.equals("")||opt2.equals("")||opt3.equals("")||ans.equals(""))
                    Toast.makeText(CreateActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                    Boolean insert = DB.addQuestions(quest, opt1, opt2, opt3, ans);
                    if(insert==true){
                        Toast.makeText(CreateActivity.this, "Quiz Created successfully", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(CreateActivity.this, "Quiz doesn't created Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}