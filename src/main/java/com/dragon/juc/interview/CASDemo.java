package com.dragon.juc.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/6 10:43
 * @description：
 * @modified By：
 * @version: $
 * 1.什么是CAS ? ===> compareAndSet
 *   比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t current"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t current"+atomicInteger.get());

    }
}
