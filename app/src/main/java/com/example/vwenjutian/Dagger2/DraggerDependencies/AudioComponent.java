package com.example.vwenjutian.Dagger2.DraggerDependencies;

import com.example.vwenjutian.Dagger2.draggerFirst.ComputerComponent;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

@Component(modules = AudioModule.class, dependencies = {ComputerComponent.class})
public interface AudioComponent {

    /**
     * 此处的方法可以不写.写了是为了暴露对象 给子依赖
     *
     * @return
     */
    public Audio providerTomato();

    /**
     * 是否想注入那个对象中,如果不想注入的话可以不写
     * @param computer2
     */
      void inject(Computer2 computer2);
}