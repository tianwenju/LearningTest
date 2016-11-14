package com.example.vwenjutian.Dagger2.DaggerScope;

import dagger.Component;

/**
 * Created by V.Wenju.Tian on 2016/11/9.
 */
@HouseScope
@Component(modules = {HouseModule.class})
public interface HouseComponent {
    void inject(Tom tom);
    void inject(Jason jason);
}
