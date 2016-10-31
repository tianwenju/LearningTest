package com.example.vwenjutian.Dragger2.app;

import android.content.Context;

import com.example.vwenjutian.Dragger2.Person;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */
@Module
public class ActivityModel {

    @Provides
    Person ProviderPerson(Context context){
        return new Person(context);
    }

}
