package com.example.vwenjutian.Dagger2.app;

import android.app.Application;

import com.example.vwenjutian.Dagger2.DaggerScope.DaggerHouseComponent;
import com.example.vwenjutian.Dagger2.DaggerScope.HouseComponent;
import com.example.vwenjutian.Dagger2.DaggerScope.HouseModule;

/**
 * Created by V.Wenju.Tian on 2016/11/9.
 */

public class App extends Application {

    private static HouseComponent houseComponent;

    @Override
    public void onCreate() {

        houseComponent = DaggerHouseComponent.builder().houseModule(new HouseModule()).build();
        super.onCreate();
    }

    public static HouseComponent getHouseComponent() {
        return houseComponent;
    }
}
