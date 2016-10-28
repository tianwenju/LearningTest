package com.example.vwenjutian.learningtest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */

@Singleton
@Component(modules = MainModel.class)
public interface MainComponet {
    // 注入的方法
    void inject(MainActivity activity);
}
