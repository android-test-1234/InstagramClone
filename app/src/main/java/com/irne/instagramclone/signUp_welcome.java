package com.irne.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class signUp_welcome extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_welcome);

        textView = findViewById(R.id.textView2);

        ParseUser parseUser = new ParseUser();
        textView.setText("Welcome " + ParseUser.getCurrentUser().getUsername());

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            FancyToast.makeText(signUp_welcome.this,"Log Out Successfully",
                                    Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            finish();
                        }
                        else {

                            FancyToast.makeText(signUp_welcome.this,e.getMessage(),
                                    Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }

                    }
                });
            }
        });



    }
}
