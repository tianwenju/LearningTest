package com.example.vwenjutian.Dragger2.app;

import com.example.vwenjutian.Dragger2.MainActivity;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */
@Component(dependencies = AppComponent.class,modules = ActivityModel.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
