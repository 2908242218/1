package java1;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author MikeCoder
 * @create 2023-02-1517:09
 * @description:java1
 * @verson:
 */
public class TCPSocket {
    //客户端
    @Test
    public void cilent() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建一个socket对象，指明服务器端的ip和端口号
            InetAddress id = InetAddress.getByName("127.0.0.1");
            socket = new Socket(id,8989);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我时客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
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
        }

    }

    //服务端
    @Test
    public void server() {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的serversocket，指明自己的端口号
            ss = new ServerSocket(8989);
            //2.调用accept()表明接收来自客户端的socket
            socket = ss.accept();
            //3.获取输入流
            is = socket.getInputStream();
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                //5.资源的关闭
            try {
                if(baos != null)
                baos.close();
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
        }

    }
}
