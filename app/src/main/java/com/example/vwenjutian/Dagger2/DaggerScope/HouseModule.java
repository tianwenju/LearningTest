package com.example.vwenjutian.Dagger2.DaggerScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/11/9.
 */
@Module
public class HouseModule {

    /**
     * 指定knife的使用范围
     *
     * @return
     */
    @HouseScope
    @Provides
    public House providerHouse() {
        return new House();
    }
}
