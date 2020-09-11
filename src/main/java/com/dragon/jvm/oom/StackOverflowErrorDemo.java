package com.dragon.jvm.oom;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/9/11 15:34
 * @description：栈溢出
 * @modified By：
 * @version: $
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError(); //Exception in thread "main" java.lang.StackOverflowError
    }
}
