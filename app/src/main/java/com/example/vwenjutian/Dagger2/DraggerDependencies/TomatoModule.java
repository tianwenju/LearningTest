package com.example.vwenjutian.Dagger2.DraggerDependencies;

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
