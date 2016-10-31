package com.example.vwenjutian.Dragger2.DraggerThrid;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

@Module
public class SaladModule {


    @Singleton
    @Type("nomal")
    @Provides
    public Apple providerNomalApple() {

        return new Apple();
    }

    @Type("color")
    @Provides
    public Apple providerColorApple(String color) {

        return new Apple(color);
    }

    //    由于我们的Apple构造函数里使用了String,所以这里要管理这个String(★否则报错)
    //    int等基本数据类型是不需要这样做的
    @Provides
    public String providerString() {
        return new String("red");
    }
}
