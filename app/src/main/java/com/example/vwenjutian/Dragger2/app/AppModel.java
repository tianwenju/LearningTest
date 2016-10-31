package com.example.vwenjutian.Dragger2.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */

@Module
public class AppModel {

    private Context context;

    public AppModel(Context context) {
        this.context = context;
    }
    @Provides
    Context providesContext(){
        return  context;
    }

}
