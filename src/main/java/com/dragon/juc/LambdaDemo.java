package com.dragon.juc;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/1/20 16:55
 * @description：Lambda表达式
 * @modified By：
 * @version: $
 */
public class LambdaDemo {

    @FunctionalInterface
    interface Foo{
        public int add(int x,int y);

        default int div(int x,int y){
            return x/y;
        }

        public static int sub(int x,int y){
            return x-y;
        }
    }

    public static void main(String[] args) {

        /*Foo foo = new Foo() {
            @Override
            public int add(int x, int y) {
                System.out.println("dragon");
                return x+y;
            }
        };*/

        //Lambda表达式写法
        Foo foo = (int x, int y)->{
            System.out.println("Lambda");
            return x+y;
        };

        System.out.println(foo.add(10, 5));
        System.out.println(foo.div(10, 5));
        System.out.println(Foo.sub(10, 5));
    }
}
