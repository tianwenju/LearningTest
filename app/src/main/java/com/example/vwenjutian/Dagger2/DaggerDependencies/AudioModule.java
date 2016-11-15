package com.example.vwenjutian.Dagger2.DaggerDependencies;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

@Module
public class AudioModule {


    @Provides
    public Audio providerAudio() {
        return new Audio();
    }

}
