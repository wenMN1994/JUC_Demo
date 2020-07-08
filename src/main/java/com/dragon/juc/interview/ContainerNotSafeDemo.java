package com.dragon.juc.interview;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/7 16:51
 * @description：CopyOnWriteArrayList
 * @modified By：
 * @version: $
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
//        List<String> list= new ArrayList<>();
//        List<String> list= new Vector<>();
//        List<String> list= Collections.synchronizedList(new ArrayList<>());
        List<String> list= new CopyOnWriteArrayList<>();
        for (int i = 1; i <=300; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(1,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
