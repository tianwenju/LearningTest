package com.example.vwenjutian.Dragger2.DraggerSix;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

@Module
public class TomatoModule {


    @Provides
    public Tomato providerTomato() {
        return new Tomato();
    }


}
