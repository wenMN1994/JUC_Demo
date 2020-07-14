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
            // 模拟网络延时
            TimeUnit.MILLISECONDS.sleep(200);
            map.put(key, value);
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
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取数据完毕"+value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

/**
 * Description:
 * 多个线程同时操作 一个资源类没有任何问题 所以为了满足并发量
 * 读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源来  就不应该有其他线程可以对资源进行读或写
 * <p>
 * 小总结:
 * 读 读能共存
 * 读 写不能共存
 * 写 写不能共存
 * 写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许被分割 被打断
 */
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
