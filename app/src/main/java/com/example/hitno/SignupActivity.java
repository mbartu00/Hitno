package com.example.hitno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText username, password, retypepassword;
    Button signup;
    Database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        retypepassword = (EditText) findViewById(R.id.retypepassword1);
        signup = (Button) findViewById(R.id.signup1);
        DB = new Database(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String retypepass = retypepassword.getText().toString();

                if(user.equals("") || pass.equals("") || retypepass.equals(""))
                    Toast.makeText(SignupActivity.this,"Popunite sva polja!", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(retypepass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true) {
                                Toast.makeText(SignupActivity.this, "Registracija uspješna!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Registracija neuspješna!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Korisnik već postoji! Prijavite se", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Lozinke se ne podudaraju!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}