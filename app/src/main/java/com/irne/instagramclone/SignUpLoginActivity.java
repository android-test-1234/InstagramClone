package com.irne.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.logging.Level;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText editText6,editText7,editText8,editText9;


    private Button button6,button7;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);

        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(editText6.getText().toString());
                parseUser.setPassword(editText8.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            FancyToast.makeText(SignUpLoginActivity.this,parseUser.get("username") + " is successfully signed up",
                                    Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            startActivity(new Intent(SignUpLoginActivity.this,signUp_welcome.class));
                        }
                        else {

                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),
                                    Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(editText7.getText().toString(), editText9.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user != null && e == null){

                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username") + " logged in Successfully",
                                    Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            startActivity(new Intent(SignUpLoginActivity.this,signUp_welcome.class));
                        }
                        else {

                            FancyToast.makeText(SignUpLoginActivity.this,e.getMessage(),
                                    Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });
    }
}
