package com.example.vwenjutian.Dagger2.DraggerQualifier;

import javax.inject.Inject;

/**
 * Created by V.Wenju.Tian on 2016/10/31.
 */

public class ComputerTest {

    @Inject
    @Type("nomal")
    Mouse nomalMouse;

    @Type("nomal")
    @Inject
    Mouse nomalMouse2;

    @Type("color")
    @Inject
    Mouse colorMouse;

    public ComputerTest() {
        ComputerTestComponent computerTestComponent = DaggerComputerTestComponent.create();
        computerTestComponent.inject(this);
        System.out.println(nomalMouse.hashCode()+"_______"+ nomalMouse2.hashCode());
    }


    public static void main(String[] args) {
        new ComputerTest();
    }
}

