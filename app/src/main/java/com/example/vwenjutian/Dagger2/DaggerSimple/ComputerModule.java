package com.example.vwenjutian.Dagger2.DaggerSimple;

import com.example.vwenjutian.Dagger2.bean.Display;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/11/16.
 */
@Module
public class ComputerModule {
    @Provides
    Display providerDisplay() {
        return  new Display();
    }
    @


}
