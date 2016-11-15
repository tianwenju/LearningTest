package com.example.vwenjutian.daggerInject;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */
//不用指定具体的module
@Component
public interface CupComponent {
    void inject(Cup cup);
   // void inject (Tea tea);
}
