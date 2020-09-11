package com.dragon.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 16:37
 * @description：直接内存溢出
 * @modified By：
 * @version: $
 */
public class DirectBufferMemoryDemo {
    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
