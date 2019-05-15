package com.planet.treeplantations.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.planet.treeplantations.R;
import com.planet.treeplantations.Sherd_pref.Pref;

public class Splash_Activity  extends AppCompatActivity {
    private Pref preg_s;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        context =Splash_Activity.this;
        preg_s=new Pref(context);
        final String id= preg_s.loadPreferences("Id");
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (id.equals("")&&id !=null) {
                    Intent mainIntent = new Intent(Splash_Activity.this,LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(Splash_Activity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }

        }, 2000);
    }
}
