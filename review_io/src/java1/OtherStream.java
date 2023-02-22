package java1;

import org.junit.Test;

import java.io.*;
import java.util.Locale;

/**
 * 1.1
 * System.in:标准的输入流，默认从键盘输入
 * System.out:标准的输出流，默认从控制台输出
 * 1.2
 * System类的setIn(InputStream is)/setOut(PrintStream ps)方式重新指定输入和输出流
 *
 *
 *
 * @author MikeCoder
 * @create 2023-02-1421:37
 * @description:java1
 * @verson:
 */
public class OtherStream {
    /**
     * 从键盘输入字符串，要求将读取的整行字符串转成大写输出。然后继续进行输入操作
     * 直至当输入“e”或者“exit”时，退出程序
     */
    @Test
    public void test1(){
        BufferedReader bis = null;
        try {
            //创建转换流
            InputStreamReader isr = new InputStreamReader(System.in);
            //创建缓冲流
            bis = new BufferedReader(isr);
            while(true){
                System.out.println("请输入:");
                String s = bis.readLine();
                if(s.equalsIgnoreCase("e") || s.equalsIgnoreCase("exit")){
                    System.out.println("退出程序");
                    break;
                }else{
                    System.out.println(s.toUpperCase(Locale.ROOT));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null)
                 bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    将ASCLL字符打印到文件中
     */
    @Test
    public void test2(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("hello.txt");
            ps = new PrintStream(fos, true);
            if(ps != null){
                System.setOut(ps);
            }
            for(int i = 0;i<255;i++){
                char c = (char)i;
                System.out.print(c);
                if(i%50==0){
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ps != null)
            ps.close();
        }
    }
}
