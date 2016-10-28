package com.example.vwenjutian.learningtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vwenjutian.learningtest.app.ActivityComponent;
import com.example.vwenjutian.learningtest.app.ActivityModel;
import com.example.vwenjutian.learningtest.app.AppComponent;
import com.example.vwenjutian.learningtest.app.AppModel;
import com.example.vwenjutian.learningtest.app.DaggerActivityComponent;
import com.example.vwenjutian.learningtest.app.DaggerAppComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MainComponet mainComponet = DaggerMainComponet.builder().mainModel(new MainModel(this)).build();
//        mainComponet.inject(this);

        AppComponent appComponent = DaggerAppComponent.builder().appModel(new AppModel(this)).build();
        ActivityComponent activityComponent =
                DaggerActivityComponent.builder().activityModel(new ActivityModel()).build();
        activityComponent.inject(this);
        Intent intent = getPackageManager().getLaunchIntentForPackage("");
        getApplicationContext().startActivity(intent);



    }
}
