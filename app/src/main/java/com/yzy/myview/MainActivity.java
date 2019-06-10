package com.yzy.myview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.myview).post(new Runnable() {
            @Override
            public void run() {
                System.out.println("A");
            }
        });

       new Handler().post(new Runnable() {
            @Override
            public void run() {
                System.out.println("B");
            }
        });
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               System.out.println("D");
           }
       });

        Log.d(TAG,"onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("C");
        Log.d(TAG,"onResume");

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}
