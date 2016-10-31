package com.example.vwenjutian.Dragger2.DraggerSecond;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

@Module
public class SaladModule {


    @Provides
    Knife providerKnife() {
        return new Knife();
    }

    @Provides
    Orange providerOranger(Knife knife) {
        return new Orange(knife);
    }

}
