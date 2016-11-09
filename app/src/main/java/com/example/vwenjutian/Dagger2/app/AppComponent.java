package com.example.vwenjutian.Dagger2.app;

import android.content.Context;

import dagger.Component;

/**
 * 全局的Component
 * Created by V.Wenju.Tian on 2016/10/20.
 */

@Component(modules = AppModel.class)
public interface AppComponent {

    Context proContext();
}
