package com.example.vwenjutian.Dagger2.DaggerScope;

import com.example.vwenjutian.Dagger2.app.App;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/9.
 */

public class Tom {
    @Inject
    House house;

    public Tom() {
        App.getHouseComponent().inject(this);
        System.out.println("Tom" + house.hashCode());
    }
}
