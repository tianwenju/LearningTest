package com.example.vwenjutian.Dagger2;

import android.content.Context;
import android.util.Log;

/**
 * Created by V.Wenju.Tian on 2016/10/20.
 */

public class Person {
    private static final String TAG = "Person";

    private  String name;
    private  int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(Context context) {
        Log.d(TAG, "Person() called");
    }
}
