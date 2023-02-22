package java1;

import org.junit.Test;

import java.io.*;

/**
 * @author MikeCoder
 * @create 2023-02-1322:00
 * @description:java1
 * @verson:
 * 一、流的分类：
 * 1.操作数据单位：字节流，字符流
 * 2.数据的流向：输入流，输出流
 * 3.流的角色：节点流，处理流
 *
 * 二、流的体系
 * 抽象基类         节点流（文件流）        缓冲流
 * InputStream      FileInputStream     BufferedInputStream
 * OutputStream     FileOutputStream    BufferedOutputStream
 * Reader           FileReader          BufferedReader
 * Writer           FileWriter          BufferedWriter
 */
public class FileWriterReaderTest {
    @Test
    public void testFileReader()  {
        FileReader reader = null;
        try {
            //1.实例化File对象，指明要操作的文件
            File file = new File("hello.txt");
            //2.提供具体的流
            reader = new FileReader(file);
            //3.数据的读入
            //read():返回读入的一个字符，如果达到文件末尾，返回-1
            int data = reader.read();
            while(data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //对read()操作升级：使用read的重载方法
    @Test
    public void testFileReader2() {
        FileReader reader = null;
        try {
            //1.File类的实例化
            File file = new File("hello.txt");
            //2.FileReader流的实例化
            reader = new FileReader(file);
            //3.读入的操作
            char[] cbuf = new char[5];
            int len;
            //方式一
//            while((len = reader.read(cbuf))!= -1){
//                for(int i=0;i<len;i++){
//                    System.out.print(cbuf[i]);
//                }
//            }
            //方式二
            while((len = reader.read(cbuf)) != -1){
                String s = new String(cbuf,0,len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            try {
                if(reader != null)
                     reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    从内存中写入数据到硬盘文件里：

    说明：
    1.输出操作，对应的File可以不存在，并不会报异常
    2.File对应的硬盘中的文件如果不存在，在输出的过程中，会字段创建此文件。
      File对应的硬盘文件如果存在：
                如果流使用的构造器是：FileWriter(file,false)/FileWriter(file):对原有文件的覆盖
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件上追加
     */
    @Test
    public void testFileWriter() throws Exception{
        //1.提供File类的对象，指明写出的文件
        File file = new File("hello1.txt");
        //2.提供FileWriter的对象，用于数据的写出
        FileWriter writer = new FileWriter(file);
        //3.写出的操作
        writer.write("I hava a dream!\n");
        writer.write("you need to hava a dream!");
        //4.流的关闭
        writer.close();
    }
    @Test
    public void testFileReaderFileWriter() {
        FileReader reader = null;
        FileWriter writer = null;
        try {
            //1.提供File类的对象，指明读入和写出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello3.txt");
            //2.提供FileReader和FileWriter的对象，用于数据的读入和写出
            reader = new FileReader(srcFile);
            writer = new FileWriter(destFile);
            //3.读入和写出操作
            char[] cbuf = new char[5];
            int len;//记录每次读入字符的个数
            while((len = reader.read(cbuf)) != -1){
                writer.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.输入流和输出流的关闭
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //使用FileInputStream和FileOutputStream实现图片文件的复制
    @Test
    public void  testFileInputStreamFileOutputStream() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            //1.实例化File对象，指明读入和写出的File文件
            File reader = new File("QQ图片20211030211908.jpg");
            File writer = new File("QQ图片20211030211908复制.jpg");
            //2.创建FileInputStream和FileOutputStream对象，用于文件的读入和写出
            inputStream = new FileInputStream(reader);
            outputStream = new FileOutputStream(writer);
            //3.读入和写出操作
            byte[] bbuf = new byte[20];
            int len;
            while((len = inputStream.read(bbuf)) != -1){
                outputStream.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //对图片进行加密处理
    @Test
    public void  testFileInputStreamFileOutputStream2() {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            //1.实例化File对象，指明读入和写出的File文件
            File reader = new File("QQ图片20211030211908加密.jpg");
            File writer = new File("QQ图片20211030211908解密.jpg");
            //2.创建FileInputStream和FileOutputStream对象，用于文件的读入和写出
            inputStream = new FileInputStream(reader);
            outputStream = new FileOutputStream(writer);
            //3.读入和写出操作
            byte[] bbuf = new byte[20];
            int len;
            while((len = inputStream.read(bbuf)) != -1){
                for( int i = 0;i<bbuf.length;i++){
                    bbuf[i] = (byte) (bbuf[i]^5);
                }
                outputStream.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
