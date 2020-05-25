package com.bizz;

import com.bizz.com.test.Fizz;

public class Bizz {
    public static void main(String[] args) {
        Fizz f = new Fizz();
        Test t = new Test();
        System.out.println(f);
        //f.woof; //Can not access without subclass
        /*f.buzz;
        t.woof;
        t.buzz;*/

    }
}

class Test extends Fizz{
void test () {
    //String s1 = buzz;
    //String s2 = woof;
}
}