package com.example.vwenjutian.Dragger2.DraggerSix;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

@Component(modules = TomatoModule.class)
public interface TomatoComponent {

    /**
     * 此处的方法可以不写.写了是为了暴露对象 给子依赖
     *
     * @return
     */
    public Tomato providerTomato();

    /**
     * 是否想注入那个对象中,如果不想注入的话可以不写
     * @param salad
     */
    //  void inject(Salad salad);
}
