package filereview;

import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * @author MikeCoder
 * @create 2023-02-1318:52
 * @description:filereview，对File类的测试
 * @verson:
 */
public class FileTest {
    /**
     * File类的三种构造器
     */
    @Test
    public void test1(){
        //构造器1：只有一个路径参数
        //1.1：填充相对路径参数，相对路径为当前Module下
        File file1 = new File("hello.txt");
        System.out.println(file1);
        //1.2：填充绝对路径参数
        File file2 = new File("D:\\IDEA\\WorkSpace\\JavaSenior\\review-practice.txt");
        System.out.println(file2);
        //构造器2：填充父路径和子路径两个参数
        File file3 = new File("D:\\IDEA\\WorkSpace\\JavaSenior\\review-practice", "subpath");
        System.out.println(file3);
        //构造器3：填充一个File父对象和子路径参数
        File file4 = new File(file3, "world.txt");
        System.out.println(file4);
    }

    /**
     * 测试File类的获取功能
     */
    @Test
    public void test2(){
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(new Date(file.lastModified()));
        System.out.println();
        String[] list = new File("D:\\IDEA\\WorkSpace\\JavaSenior").list();
        for(String s : list){
            System.out.println(s);
        }
    }
}
