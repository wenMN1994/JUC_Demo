package com.dragon.juc;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/2/5 22:44
 * @description：
 * @modified By：
 * @version: $
 */
class User {
    private int id;
    private String userName;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class StreamDemo {

    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        //void accept(T t); 消费型 有参数 无返回值
        Consumer<String> consumer = t -> {
            System.out.println(t);
        };
        consumer.accept("abc");

        //T get(); 供给型 无参数 有返回值
        Supplier<String> supplier = () -> {
            return UUID.randomUUID().toString().substring(0,8);
        };
        System.out.println(supplier.get());

        //R apply(T t);函数型 有参数 有返回值
        Function<String, Integer> function = t -> {
            return t.length();
        };
        System.out.println(function.apply("abcd"));

        //boolean test(T t); 断言型 有参数 有boolean返回值
        Predicate<String> predicate = t -> {
            return t.startsWith("a");
        };
        System.out.println(predicate.test("abcd"));
    }
}
