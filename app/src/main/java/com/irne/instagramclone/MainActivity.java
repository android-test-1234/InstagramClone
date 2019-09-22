package com.irne.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText editText,editText2,editText3,editText4,editText5;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);

        button = findViewById(R.id.button2);
        button.setOnClickListener(MainActivity.this);


    }

    @Override
    public void onClick(View view) {


        try {

            ParseObject kickboxer = new ParseObject("KickBoxer");
            kickboxer.put("name", editText.getText().toString());
            kickboxer.put("punch_speed", Integer.parseInt(editText2.getText().toString()));
            kickboxer.put("punch_power", Integer.parseInt(editText3.getText().toString()));
            kickboxer.put("kick_speed", Integer.parseInt(editText4.getText().toString()));
            kickboxer.put("kick_power", Integer.parseInt(editText5.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(MainActivity.this, "Sucessfully Saved",
                                FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {

                        FancyToast.makeText(MainActivity.this, e.getMessage(),
                                FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });

        }
        catch (Exception e){

            FancyToast.makeText(MainActivity.this, e.getMessage(),
                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
