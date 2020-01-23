package com.dragon.juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        List<String> list = new ArrayList<>();

        for (int i = 1; i <=50 ; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);

            },String.valueOf(i)).start();
        }
    }
}
