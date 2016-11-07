package com.example.vwenjutian.Dragger2.app;

import android.content.Context;

import com.example.vwenjutian.Dragger2.Person;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */
@Module
public class ActivityModel {

    @Provides
    Person ProviderPerson(Context context) {
        return new Person(context);
    }

    @Provides
    List<String> ProviderData() {

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("这是第" + i);
        }
        return datas;
    }

}
