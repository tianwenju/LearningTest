package com.example.vwenjutian.Dragger2.draggerFirst;

import com.example.vwenjutian.Dragger2.bean.Banana;
import com.example.vwenjutian.Dragger2.bean.Pear;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by V.Wenju.Tian on 2016/10/28.
 */

@Module
public class SaladModule {

    private String from;

    private String taste;

    /**
     * 步骤:
     * 步骤1：查找Module中是否存在相同创建该类的方法。
     * 步骤2：若存在创建类方法，查看该方法是否存在参数
     * 步骤2.1：若存在参数，则按从**步骤1**开始依次初始化每个参数
     * 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     * 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，
     * 看构造函数是否存在参数 mk
     * 步骤3.1：若存在参数，则从**步骤1**开始依次初始化每个参数
     * 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     * -------------------------------------------------------------------
     * 注意:
     * 1.在没有注解的情况下不能含有返回值一样的方法.
     * 2.查找是根据返回这和参数以及注解来进行的与方法名无关
     * @return
     */
    @Provides
    public Pear providerPer() {
        return new Pear();
    }

    @Provides
    public String providerString() {
        return from;
    }

    @Named("taste")
    @Provides
    public String providerStringtaste() {
        return taste;
    }

    public SaladModule() {
    }

    public SaladModule(String from, String taste) {
        this.from = from;
        this.taste = taste;
    }

    @Provides
    public Banana providerBananaFrom(String from) {
        return new Banana(from);
    }


}
