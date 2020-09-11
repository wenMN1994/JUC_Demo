package com.dragon.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 16:11
 * @description：超过98%的时间用来做GC并且回收不到2%的堆内存
 * @modified By：
 * @version: $
 */
public class GCOverheadLimitDemo {
    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     * @param args
     */
    public static void main(String[] args) {
        // java.lang.OutOfMemoryError: GC overhead limit exceeded
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("**********i："+i);
            e.printStackTrace();
        }
    }
}
