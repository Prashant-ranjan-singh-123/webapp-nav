package com.bharat.mall;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.view.WindowManager;
import android.content.SharedPreferences;
import android.widget.FrameLayout;



import com.bharat.mall.MainActivity;


public class SplashActivity extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Window window = getWindow() ;


        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);





        Thread splashTread = new Thread(){


            @Override

            public void run() {


                try {

                    sleep(3000);
                    Intent intent;

                    SharedPreferences prefs = getSharedPreferences("my_preferences", MODE_PRIVATE);
                    boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);
                    if (isFirstLaunch) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("isFirstLaunch", false);
                        editor.apply();
                        intent=new Intent(SplashActivity.this,OnboardingActivity.class);
                    }
                    else {
                        intent=new Intent(SplashActivity.this,MainActivity.class);
                    }
                    startActivity(intent);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }

        };
        splashTread.start();
    }


}