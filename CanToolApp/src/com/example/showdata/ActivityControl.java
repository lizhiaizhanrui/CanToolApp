package com.example.showdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;

public class ActivityControl {

	public static List<Activity> lists=new ArrayList<Activity>();
    //每当启动一个activity就添加到集合中
    public static void addActivity(Activity baseActivity){

        lists.add(baseActivity);

    }
    //每当销毁一个activity就从集合中移除
    public static void removeActivity(Activity baseActivity){

        lists.remove(baseActivity);
    }

    //找到指定的activity实例（对象）
    public Activity getActivity(Class clazz) {

        for (Activity list : lists) {
            if (list.getClass() == clazz) {
                return list;
            }

        }
        return null;
    }

    //删除所有Activity
    public static void killAllAcitvity(){
        Iterator<Activity> iterator = lists.iterator();

        while (iterator.hasNext()){
            Activity baseActivity = iterator.next();
            baseActivity.finish();
        }

    }
	
}
