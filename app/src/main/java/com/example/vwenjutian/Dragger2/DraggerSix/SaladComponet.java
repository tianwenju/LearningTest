package com.example.vwenjutian.Dragger2.DraggerSix;

import com.example.vwenjutian.Dragger2.draggerFirst.SaladModule;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

@Component(modules = {SaladModule.class}, dependencies = {TomatoComponent.class})
public interface SaladComponet {

    void inject(Salad salad);
}
