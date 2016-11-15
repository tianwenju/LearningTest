package com.example.vwenjutian.Dagger2.DaggerDependencies;

import com.example.vwenjutian.Dagger2.bean.Display;
import com.example.vwenjutian.Dagger2.bean.keyboard;
import com.example.vwenjutian.Dagger2.bean.Master;
import com.example.vwenjutian.Dagger2.daggerFirst.ComputerComponent;
import com.example.vwenjutian.Dagger2.daggerFirst.ComputerModule;
import com.example.vwenjutian.Dagger2.daggerFirst.DaggerComputerComponent;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

public class Computer2 {

    @Inject
    Audio audio;
    @Inject
    keyboard keyboard;
    @Inject
    Display display;
    @Inject
    Master master;

    public Computer2() {
        ComputerComponent component =  DaggerComputerComponent.builder().computerModule(new ComputerModule("中国","台达")).build();
        DaggerAudioComponent.builder().computerComponent(component).audioModule(new AudioModule()).build().inject(this);

    }

    public static void main(String[] args) {
        new Computer2();
    }
}
