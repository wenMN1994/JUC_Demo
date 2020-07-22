package com.dragon.jvm;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/22 11:48
 * @description：
 * @modified By：
 * @version: $
 */
public class HeapSize {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY：" + totalMemory + "字节");
        System.out.println("MAX_MEMORY：" + maxMemory + "字节");
    }

}
