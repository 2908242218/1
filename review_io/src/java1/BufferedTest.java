package java1;

import org.junit.Test;

import java.io.*;

/**
 * @author MikeCoder
 * @create 2023-02-1417:29
 * @description:java1
 * @verson:
 */
public class BufferedTest {
    //实现文件的复制
    @Test
    public void testBufferedInputStreamBufferedOutputStream()  {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //造文件
            File file = new File("QQ图片20211030211908.jpg");
            File file1 = new File("QQ图片20211030211908复制2.jpg");
            //造流
            //1.造节点流
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(file1);
            //2.造缓冲流
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(outputStream);
            //读取和写入
            byte[] bbuf= new byte[5];
            int len;
            while((len = bis.read(bbuf)) != -1){
                bos.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //流的关闭
            try {
                if(bis != null)
                    bis.close();//关闭外层的流，会自动将内层的流关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
