package com.example.vwenjutian.Dragger2.DraggerSecond;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */
@Component(modules = {SaladModule.class})
public interface SaladComponet {

    Knife providerKnife();

    Orange providerOranger();

    void inject(Salad salad);

}
