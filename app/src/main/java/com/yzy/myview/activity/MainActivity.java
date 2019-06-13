package com.yzy.myview.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.yzy.myview.R;
import com.yzy.myview.view.RollMenu;

public class MainActivity extends AppCompatActivity {

    private static String TAG=MainActivity.class.getSimpleName();
    private RollMenu rollMenu;
    private FrameLayout topFrameLayout,bottomFrameLayout;
    BlankFragment topFragment, bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollMenu=findViewById(R.id.roll_menue);
        topFrameLayout=findViewById(R.id.top_frame);
        bottomFrameLayout=findViewById(R.id.bottom_frame);
        if(null==savedInstanceState){
             topFragment=new BlankFragment("topFragment","");
             bottomFragment=BlankFragment.newInstance("bottomFragment","");
            showFragment(topFrameLayout.getId(),topFragment);
            showFragment(bottomFrameLayout.getId(),bottomFragment);
        }

        Log.d(TAG,"onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private void showFragment(int id,Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(id,fragment);
        fragmentTransaction.commit();

    }
}
