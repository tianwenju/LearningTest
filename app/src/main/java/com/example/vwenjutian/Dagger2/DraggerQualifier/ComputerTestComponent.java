package com.example.vwenjutian.Dagger2.DraggerQualifier;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */
@Singleton
@Component(modules = ComputerTestModule.class)
public interface ComputerTestComponent {


//    @Type("nomal")
//    Mouse providerNonmalApple();
//
//    @Type("color")
//    Mouse providerColorApple();

   // String providerString();
    ////注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
//这里我们把上面的三个依赖注入到Salad中
    void inject(ComputerTest computerTest);
}
