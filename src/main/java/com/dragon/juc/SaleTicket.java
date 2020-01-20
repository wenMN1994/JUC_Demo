package com.dragon.juc;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/20 14:31
 * @description：卖票程序
 * @modified By：
 * @version: $
 */
class Ticket{
    //创建资源
    private int number = 30;

    public synchronized void sale(){
        synchronized(this){

        }

        if(number>0){
            System.out.println(Thread.currentThread().getName()
                    +"\t 卖出"+number--+"号票\t还剩"+number
            );
        }
    }
}

public class SaleTicket {

    /**
     * 卖票程序复习线程知识：三个售票员  卖出    30张票
     *
     * @param args
     * @throws Exception 线程 操作 资源类,高内聚低耦合
     */
    public static void main(String[] args) {

        //实例化一个资源对象
        Ticket ticket = new Ticket();

        //匿名内部类方式创建线程对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
