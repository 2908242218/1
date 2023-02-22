package java1;

import org.junit.Test;

import java.io.File;

/**
 * @author MikeCoder
 * @create 2022-11-0117:09
 */
public class FileTest {
    @Test
    public void test(){
        File file = new File("D:\\360MoveData\\Users\\大树王子\\Desktop\\财务管理");
        boolean mkdir = file.mkdir();
        if(mkdir){
            System.out.println("创建成功");
        }
        System.out.println(For(file)/(1024*1024));

    }
    public int For(File f){
        int size = 0;


        File[] files = f.listFiles();
        for(int i = 0;i < files.length;i++){
            if(files[i].isDirectory()){
                 size += For(files[i]);
            }else{
                System.out.println(files[i].getName());
                size+=files[i].length();
            }
        }

        return size;
    }
}
