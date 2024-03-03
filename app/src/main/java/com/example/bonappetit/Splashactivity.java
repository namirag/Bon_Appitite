package com.example.bonappetit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.bonappetit.activities.Welcomeactivity;


public class Splashactivity extends AppCompatActivity {
    //AppCompatActivity is the base class for activities with the support library action bar features.
//ActionBar can be added to your activity by extending this class for your activity and setting the activity theme
//to Theme when running on API level 7 or higher.
    View bowl,bike;
    Animation  middleanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Android Bundles are generally used for passing data from one activity to another.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        middleanim = AnimationUtils.loadAnimation(this,R.anim.middleanim);

        bowl = findViewById(R.id.bowl);

        bike = findViewById(R.id.bike);
        bike.setAnimation(middleanim);
        bowl.animate().alpha(0.0f).setDuration(2000).withEndAction(new Runnable() {
            @Override
            public void run() {
                bowl.setVisibility(View.GONE);
            }
        });

        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(3800);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //intent is a class of content it helps to redirect user form one activity to another activity
                Intent intent = new Intent(Splashactivity.this, Welcomeactivity.class);
                startActivity(intent);
                finish();
            }
        });thread.start();
    }
}