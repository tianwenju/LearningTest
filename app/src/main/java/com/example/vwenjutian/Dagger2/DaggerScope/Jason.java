package com.example.vwenjutian.Dagger2.DaggerScope;

import com.example.vwenjutian.Dagger2.app.App;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/9.
 */

public class Jason {

    @Inject
    House house;

    public Jason() {

        App.getHouseComponent().inject(this);
        System.out.println("Jason"+house.hashCode());
    }

}
