package com.dragon.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/30 11:13
 * @description：读写锁
 * @modified By：
 * @version: $
 */
class MyCache {
    private volatile Map<String,String> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 准备写入数据"+key);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName()+"\t 写入数据完成"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 准备读取数据"+key);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName()+"\t 读取数据完毕"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 1; i < 5; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                myCache.put(key,UUID.randomUUID().toString().substring(0,8));
            },String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(2);

        for (int i = 1; i < 5; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                myCache.get(key);
            },String.valueOf(i)).start();
        }
    }
}
