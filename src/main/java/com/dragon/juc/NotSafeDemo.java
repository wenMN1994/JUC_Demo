package com.dragon.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/23 22:13
 * @description：举例说明集合类是不安全的
 * @modified By：
 * @version: $
 */
public class NotSafeDemo {

    public static void main(String[] args) {


    }

    private static void noSafeList() {
//        List<String> list = new ArrayList<>(); //ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常
//        List<String> list = new Vector<>(); //使用Vector可以解决线程不安全问题，但是实际工作中不使用此方法
//        List<String> list = Collections.synchronizedList(new ArrayList<>()); // 使用Collections可以解决线程不安全问题，但是实际工作中不使用此方法
        List<String> list = new CopyOnWriteArrayList(); //实际工作中推荐使用CopyOnWriteArrayList（写时复制）

        for (int i = 1; i <=50 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);

            },String.valueOf(i)).start();
        }
    }
}
