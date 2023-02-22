package fileexer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author MikeCoder
 * @create 2023-02-1320:30
 * @description:flieexer
 * @verson:
 */
public class FileTest {

    /**
     * 利用File构造器，new 一个文件目录file
     * 在其中创建多个文件和目录
     * 编写方法，实现删除file中指定文件的操作
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        File file = new File("hi.txt");
        File file1 = new File(file.getParent(), "haha.txt");
        boolean b = file1.createNewFile();
        if(b){
            System.out.println("创建成功");
        }else{
            file1.delete();
            System.out.println("删除成功");
        }
        boolean e = file1.exists();
        System.out.println(e);

    }

    /**
     * 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
     */
    @Test
    public void test2(){
        File file = new File("D:\\360MoveData\\Users\\大树王子\\Desktop\\图片和文档");
        String[] list = file.list();
        for(String s : list){
            if(s.endsWith(".jpg")){
                System.out.println(s);
            }
        }
    }

    /**
     * 遍历指定目录所有文件名称，包括子文件目录中的文件
     */
    @Test
    public void test3(){
        File file = new File("D:\\1");
        if(file.exists()){
            long size = getFileSize(file);
            System.out.println("目录下文件总大小="+size/(1024)+"MB");
        }else{
            System.out.println("文件不存在");
        }

    }

    /**
     * 遍历子目录文件的方法
     * @param dir
     */
    public void getAllFileName(File dir){
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                getAllFileName(f);
            }else{
                System.out.println(f.getName());
            }
        }
    }
    public long getFileSize(File dir){
        File[] files = dir.listFiles();
        long size = 0;
        for(File f : files){
            if(f.isDirectory()){
                 size += getFileSize(f);
            }else{
                size += f.length();
            }
        }
        return size;
    }
}
