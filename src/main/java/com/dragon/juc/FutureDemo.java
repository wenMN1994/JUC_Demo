package com.dragon.juc;

import java.util.concurrent.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/7/23 11:49
 * @description：Future使用
 * @modified By：
 * @version: $
 * 对线程池提交一个Callable任务，可以获得一个Future对象；
 * 可以用Future在将来某个时刻获取结果。
 */
class Task implements Callable<String> {
    public String call() throws Exception {
        return longTimeCalculation();
    }

    private String longTimeCalculation() {
        System.out.println("正在执行longTimeCalculation");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // 定义任务:
        Callable<String> task = new Task();
        // 提交任务并获得Future:
        Future<String> future = executor.submit(task);
        // 从Future获取异步执行返回的结果:
        try {
            String result = future.get(); // 可能阻塞
            System.out.println("========="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        executor.shutdown();
    }
}
