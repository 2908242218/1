package com.liuwei.java;

/**
 * @author MikeCoder
 * @create 2022-11-0923:37
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        Singer singer = new Singer();
        //创建代理类的对象
        assistant assistant = new assistant(singer);
        assistant.sing();
    }

}
//创建被代理类和代理类共同实现的接口
interface Man{
    void sing();
}
//创建代理类
class assistant implements Man{
    private Man singer;

    public assistant() {
    }

    public assistant(Man singer) {
        this.singer = singer;
    }

    @Override
    public void sing() {
        System.out.println("助理做好准备工作");
        singer.sing();
        System.out.println("助理做好收尾工作");
    }
}
//创建被代理类
class Singer implements Man{

    @Override
    public void sing() {
        System.out.println("歌手开了一个精彩的演唱会");
    }
}
