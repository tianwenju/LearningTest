package com.example.vwenjutian.learningtest.draggerFirst;

import com.example.vwenjutian.learningtest.bean.Banana;
import com.example.vwenjutian.learningtest.bean.Pear;
import com.example.vwenjutian.learningtest.bean.SaladSacue;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

@Module
public class SaladModule {

    @Provides
    public Banana providerBanana() {
        return new Banana();

    }


    @Provides
    public Pear providerPer() {
        return new Pear();
    }

    @Provides
    public SaladSacue providerSaladSacue() {
        return new SaladSacue();
    }

}
