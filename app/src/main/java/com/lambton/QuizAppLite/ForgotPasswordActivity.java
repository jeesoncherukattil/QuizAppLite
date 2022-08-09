package com.lambton.QuizAppLite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText newpassword, newemail, security;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        newpassword = (EditText) findViewById(R.id.newpassword);
        newemail = (EditText) findViewById(R.id.email);
        security = (EditText) findViewById(R.id.security);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = newemail.getText().toString();
                String pass = newpassword.getText().toString();
                String sec = security.getText().toString();

                if(email.equals("")||pass.equals("")||sec.equals(""))
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                        Boolean checkemail = DB.checkusername(email);
                        if(checkemail==true){
                            Boolean insert = DB.updatepassword(email,  sec ,pass);
                            if(insert==true){
                                Toast.makeText(ForgotPasswordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(ForgotPasswordActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }

                } }
        });
    }
}