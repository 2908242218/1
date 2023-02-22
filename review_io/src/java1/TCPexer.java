package java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author MikeCoder
 * @create 2023-02-1517:40
 * @description:java1
 * @verson:
 */
public class TCPexer {
    //客户端
    @Test
    public void cilent() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
            os = socket.getOutputStream();
            fis = new FileInputStream("D:\\360MoveData\\Users\\大树王子\\Desktop\\优化后图片\\微信图片_20221230180213.jpg");
            byte[] buffer = new byte[10];
            int len;
            while((len = fis.read(buffer)) != -1){
                os.write(buffer);
            }
            socket.shutdownOutput();
             is = socket.getInputStream();
            byte[] buffer2 = new byte[5];
            int len2;
            baos = new ByteArrayOutputStream();
            while((len = is.read(buffer2)) != -1){
                baos.write(buffer2,0,len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(baos != null)
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //服务端
    @Test
    public void serve() {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        OutputStream os = null;
        try {
            ss = new ServerSocket(9090);
            socket = ss.accept();
            is = socket.getInputStream();
            fos = new FileOutputStream("客户端发送过来的图片1.jpg");
            byte[] buffer = new byte[10];
            int len;
            while((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            System.out.println("图片已传输完成");
            os = socket.getOutputStream();
            os.write("你好，图片已接受".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(ss != null)
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(os != null)
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
