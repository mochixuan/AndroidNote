package com.wx.testmaster.junitTest;

import com.wx.testmaster.Calculater;

import org.junit.Assert;

/**
 *
 * 以上是一个要测试的类Calculater和测试用例CalculaterTest。
 * 在Intellij或Android Studio对类右键->run CalculaterTest，
 * 用例中所有被@org.junit.Test注解的方法，就会被执行。
 * Created by wangxuan on 2018/1/30.
 */

public class CalculaterTest {

    Calculater calculater = new Calculater();

    @org.junit.Test
    public void testAdd() {
        int a = 1;
        int b = 2;

        int result =calculater.add(a,b);
        Assert.assertEquals(result,3);

        //verify的作用，是验证函数是否被调用（以及调用了多少次）。

    }

}
