package com.example.vwenjutian.Dragger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vwenjutian.Dragger2.app.ActivityComponent;
import com.example.vwenjutian.Dragger2.app.ActivityModel;
import com.example.vwenjutian.Dragger2.app.AppComponent;
import com.example.vwenjutian.Dragger2.app.AppModel;
import com.example.vwenjutian.Dragger2.app.DaggerActivityComponent;
import com.example.vwenjutian.Dragger2.app.DaggerAppComponent;
import com.example.vwenjutian.Dragger2.app.ListAdapter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Person person;

    @Inject
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        AppComponent appComponent = DaggerAppComponent.builder().appModel(new AppModel(this)).build();
        ActivityComponent activityComponent =
                DaggerActivityComponent.builder().appComponent(appComponent).activityModel(new ActivityModel()).build();
        activityComponent.inject(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyleView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }
}
