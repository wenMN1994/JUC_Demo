package com.dragon.jvm.oom;

import java.util.Random;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 15:45
 * @description：堆内存移除
 * @modified By：
 * @version: $
 */
public class JavaHeapSpaceDemo {
    /**
     * -Xms10m -Xmx10m
     * @param args
     */
    public static void main(String[] args) {
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        String str = "dragon";
        while (true){
            str += str + new Random().nextInt(111)+new Random().nextInt(2222);
            str.intern();
        }
    }
}
