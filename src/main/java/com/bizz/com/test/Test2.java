package com.bizz.com.test;

public class Test2 {
    public static void main(String[] args) {
        Fizz f = new Fizz();
        String s = f.buzz;
        System.out.println(f.t);
        String s1 = f.woof;
        //Test3 t3 = new Test3();
        //String s2 = t3.buzz;
        //String s3 = t3.woof;
    }
}


abstract class Test3 {
    abstract void foo();
}