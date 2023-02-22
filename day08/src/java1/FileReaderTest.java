package java1;

import org.junit.Test;

import java.io.*;

/**
 * @author MikeCoder
 * @create 2022-11-0210:02
 */
public class FileReaderTest {
    @Test
    public void Test1() {
        //实例化文件对象
        File file = new File("TestFile");
        FileReader fileReader = null;
        try {
            //实例化流
            fileReader = new FileReader(file);

            char[] chars = new char[8];
            int len;
            while ((len = fileReader.read(chars)) != -1) {
                String s = new String(chars,0,len);
                System.out.print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test2(){
        File file = new File("TestFile");
        File file1 = new File("TestFileCoply.txt");
        FileReader reader = null;
        FileWriter Writer = null;
        try {
            reader = new FileReader(file);
            Writer = new FileWriter(file1);
            char[] c = new char[5];
            int len;
            while((len = reader.read(c)) != -1){
                Writer.write(c,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test3(){
        File file = new File("D:\\360MoveData\\Users\\大树王子\\Desktop\\图片和文档","可行性研究.docx");
        File file1 = new File("可行性研究副本.docx");
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file1);
            byte[] bytes = new byte[16];
            int len;
            while((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
    @Test
    public void test(){
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("TestFileCoply.txt")));
            bufferedWriter = new BufferedWriter(new FileWriter(new File("TestFileCoply01.txt")));
            String Date;
            while((Date = bufferedReader.readLine()) != null){
                bufferedWriter.write(Date);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bufferedWriter != null){

                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
