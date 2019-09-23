package com.irne.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private EditText editText,editText2,editText3,editText4,editText5;
    private Button button;

    private Button button3;
    private TextView textView;

    private String allKickBoxers;

    private Button button4;


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

        textView = findViewById(R.id.textView);
        button3 = findViewById(R.id.button3);

        button4 = findViewById(R.id.button);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        SignUpLoginActivity.class);
                startActivity(intent);
                FancyToast.makeText(MainActivity.this,"Activity Changed Successfully",
                        Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
            }
        });

      /*  button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery <ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("McfUcgbMI9", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){

                            textView.setText("Name:" + object.get("name") + "\n"+
                                    "Punch Speed:" + object.get("punch_speed") + "\n"+
                                    "Punch Power:" + object.get("punch_power") + "\n"+
                                    "Kick Speed:" + object.get("kick_speed") + "\n"+
                                    "Kick Power:" + object.get("kick_power") + "\n");
                        }
                        else {

                            FancyToast.makeText(MainActivity.this,e.getMessage(),
                                    Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });


    }*/

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseQuery<ParseObject> parseobject = ParseQuery.getQuery("KickBoxer");
                parseobject.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        //parseobject.whereGreaterThanOrEqualTo("punch_speed",100);
                        //parseobject.setLimit(1);

                        if (e == null) {

                            if (objects.size() > 0) {

                                allKickBoxers = "";

                                for (ParseObject parseObject : objects) {

                                    allKickBoxers = allKickBoxers + parseObject.get("name") + "\n";
                                }

                                FancyToast.makeText(MainActivity.this, allKickBoxers, Toast.LENGTH_LONG,
                                        FancyToast.SUCCESS, true).show();
                            } else {

                                FancyToast.makeText(MainActivity.this, e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                        }
                    }

                });
            }
        });


    }
}
