package com.example.vwenjutian.learningtest;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */

@Module
public class MainModel {
    private static final String TAG = "MainModel";
    private Context context;

    public MainModel(Context context) {
        this.context = context;
    }

    @Provides
    Context providerContext() {
        return context;
    }

    /**
     * 根据@Inject注解，查找需要依赖注入的对象。
     * 从MainModule中根据返回值，找到providerPerson(Context context)对象。
     * 发现其需要传入参数Context，找到moudule中具有返回值为Context的方法providesContext()。
     * 最后就成功的构建了实例化对象。
     * context 会根据
     * ???
     * 可能会有疑问，我既然module中已经保存了Context对象，那么为什么不直接使用Context对象呢，
     * 因为解耦，如果使用了保存的对象，会导致下次Context获取发生变化时，需要修改providerPerson(Context context)中的代码
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    Person providerPerson(Context context) {
        Log.d(TAG, "providerPerson() called with: context = [" + context + "]");
        return new Person(context);
    }

//    /**
//     * 不能同名
//     * @param context
//     * @param s
//     * @return
//     */
//    @Provides
//    Person providerPerson(Context context, String s) {
//        Log.d(TAG, "providerPerson() called with: context = [" + context + "], s = [" + s + "]");
//        return new Person(context);
//    }

//    /**
//     * 返回值不能一样
//     * @param context
//     * @param s
//     * @return
//     */
//    @Provides
//    Person providerPerson1(Context context, String s) {
//        Log.d(TAG, "providerPerson() called with: context = [" + context + "], s = [" + s + "]");
//        return new Person(context);
//    }

}
