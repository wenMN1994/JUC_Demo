package com.dragon.jvm.oom;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 17:00
 * @description：
 * @modified By：
 * @version: $
 */
public class UnableCreateNewThreadDemo {
    /**
     * java.lang.OutOfMemoryError:unable to create new native thread
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; ; i++) {
            System.out.println("*********i："+i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
