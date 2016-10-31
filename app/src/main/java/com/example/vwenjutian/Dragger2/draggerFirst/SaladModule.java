package com.example.vwenjutian.Dragger2.draggerFirst;

import com.example.vwenjutian.Dragger2.bean.Banana;
import com.example.vwenjutian.Dragger2.bean.Pear;
import com.example.vwenjutian.Dragger2.bean.SaladSacue;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

@Module
public class SaladModule {

    /**
     * 步骤1：查找Module中是否存在创建该类的方法。
     * 步骤2：若存在创建类方法，查看该方法是否存在参数
     * 步骤2.1：若存在参数，则按从**步骤1**开始依次初始化每个参数
     * 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     * 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，
     * 看构造函数是否存在参数
     * 步骤3.1：若存在参数，则从**步骤1**开始依次初始化每个参数
     * 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     *
     * @return
     */
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
