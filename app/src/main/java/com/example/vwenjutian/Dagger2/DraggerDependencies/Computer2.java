package com.example.vwenjutian.Dagger2.DraggerDependencies;

import com.example.vwenjutian.Dagger2.bean.Display;
import com.example.vwenjutian.Dagger2.bean.keyboard;
import com.example.vwenjutian.Dagger2.bean.Master;
import com.example.vwenjutian.Dagger2.draggerFirst.ComputerComponent;
import com.example.vwenjutian.Dagger2.draggerFirst.ComputerModule;
import com.example.vwenjutian.Dagger2.draggerFirst.DaggerComputerComponent;

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
        ComputerComponent component =  DaggerComputerComponent.builder().computerModule(new ComputerModule("菲律宾","苦的")).build();
        DaggerAudioComponent.builder().computerComponent(component).audioModule(new AudioModule()).build().inject(this);

    }

    public static void main(String[] args) {
        new Computer2();
    }
}
