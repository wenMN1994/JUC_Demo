package com.dragon.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/24 10:07
 * @description：多线程锁
 * @modified By：
 * @version: $
 */
class Phone {

    public static synchronized void sendSMS() throws Exception {
//        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }
    public static synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello(){
        System.out.println("--------Hello DragonWen!!!");
    }

}

/**
 * 1 标准访问，先打印短信还是邮件----先打印短信再发送邮件
 * 2 停4秒在短信方法内，先打印短信还是邮件----先发送邮件再打印短信
 * 3 新增普通的hello方法，是先打短信还是hello----先打印短信再发送邮件
 * 4 现在有两部手机，先打印短信还是邮件----先打印短信再发送邮件
 * 5 两个静态同步方法，1部手机，先打印短信还是邮件----先打印短信再发送邮件
 * 6 两个静态同步方法，2部手机，先打印短信还是邮件----先打印短信再发送邮件
 * 7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件----先打印短信再发送邮件
 * 8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件----先打印短信再发送邮件
 */
public class Lock_8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.getHello();
//                phone.sendEmail();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}
