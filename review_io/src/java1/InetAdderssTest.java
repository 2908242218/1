package java1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取InetAddress对象实例的两个方法
 * 1.通过InetAddress的static方法：getByName(String name)/geLocalHost();
 *
 *
 *
 *
 *
 * @author MikeCoder
 * @create 2023-02-1516:18
 * @description:java1
 * @verson:
 */
public class InetAdderssTest {
    public static void main(String[] args){
        try {
            InetAddress inet1 = InetAddress.getByName("127.0.0.1");
            InetAddress inet3 = InetAddress.getByName("www.atguigu.com");
            System.out.println(inet1.getHostName());
            System.out.println(inet1.getAddress());
            System.out.println(inet3);
            InetAddress inet2 = InetAddress.getLocalHost();
            System.out.println(inet1);
            System.out.println(inet2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
