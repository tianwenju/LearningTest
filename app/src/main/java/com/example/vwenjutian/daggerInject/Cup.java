package com.example.vwenjutian.daggerInject;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/11/15.
 */

public class Cup {
    @Inject
    Tea tea;

    public Cup() {
        DaggerCupComponent.create().inject(this);
        System.out.println(tea.hashCode());
    }

    /**
     * 方法注入
     * @param tea
     */
    @Inject
    void setData(Tea tea){

        System.out.println(tea.hashCode());
    }
    public static void main(String[] args) {
        System.out.println(new Cup().tea.useA());
    }
}
