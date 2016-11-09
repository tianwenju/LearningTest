package com.example.vwenjutian.Dagger2.DraggerDependencies;

import com.example.vwenjutian.Dagger2.bean.Banana;
import com.example.vwenjutian.Dagger2.bean.Pear;
import com.example.vwenjutian.Dagger2.bean.SaladSacue;
import com.example.vwenjutian.Dagger2.draggerFirst.DaggerSaladComponent;
import com.example.vwenjutian.Dagger2.draggerFirst.SaladComponent;
import com.example.vwenjutian.Dagger2.draggerFirst.SaladModule;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

public class Salad {

    @Inject
    Tomato tomato;
    @Inject
    Banana banana;
    @Inject
    Pear pear;
    @Inject
    SaladSacue saladSacue;

    public Salad() {
        SaladComponent component =  DaggerSaladComponent.builder().saladModule(new SaladModule("菲律宾","苦的")).build();
        DaggerTomatoComponent.builder().saladComponent(component).tomatoModule(new TomatoModule()).build().inject(this);

    }

    public static void main(String[] args) {
        new Salad();
    }
}
