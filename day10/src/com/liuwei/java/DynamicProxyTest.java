package com.liuwei.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author MikeCoder
 * @create 2022-11-109:50
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        apple apple = new apple();
        fruit proxyFactory = (fruit) com.liuwei.java.proxyFactory.getProxyFactory(apple);
        proxyFactory.fruitDes();
    }
}
//创建代理类
class proxyFactory{

    public static Object getProxyFactory(Object obj){
        myInvocationHandler handler = new myInvocationHandler();
        handler.ban(obj);
        Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
        return o;
    }
}
//创建代理类内newProxyInstancen()的第三个参数对象的类，一个InvocationHandlder的实现类
class myInvocationHandler implements InvocationHandler{
    private Object obj;//被代理类的对象
    //为被代理类的对象赋值
    public void ban(Object obj){
        this.obj = obj;
    }
    @Override//调用proxy（被代理类的对象）的method(),并将次方法返回值赋给invoke(),作为invoke()方法的返回值
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(obj, args);
        return invoke;
    }
}
//
interface fruit{
    void fruitDes();
}
class apple implements fruit{

    @Override
    public void fruitDes() {
        System.out.println("apple is vary lusicous");
    }
}
