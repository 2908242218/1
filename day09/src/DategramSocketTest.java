import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author MikeCoder
 * @create 2022-11-0621:01
 */
public class DategramSocketTest {
    @Test
    //发送端
    public void test1() {
        DatagramSocket datagramSocket = null;
        FileInputStream fileInputStream = null;
        try {
            datagramSocket = new DatagramSocket();
            fileInputStream = new FileInputStream("客户端传入的qq图片.jpg");
            byte[] bytes = new byte[500];
            int len;
            InetAddress inetAddress = InetAddress.getLocalHost();
            while((len = fileInputStream.read(bytes)) != -1);{
                DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length,inetAddress,9090);
                datagramSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(datagramSocket != null){
                try {
                    datagramSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    @Test
    //接收端
    public void test2()  {
        DatagramSocket datagramSocket = null;
        FileOutputStream fileOutputStream = null;
        try {
            datagramSocket = new DatagramSocket(9090);
            fileOutputStream = new FileOutputStream("客户端传入的qq图片3.jpg");
            byte[] bytes = new byte[500];
            DatagramPacket datagramPacket;
            int i = 859;
            while(i == 0){
                datagramPacket = new DatagramPacket(bytes,0,bytes.length);
                datagramSocket.receive(datagramPacket);
                fileOutputStream.write(datagramPacket.getData(),0,datagramPacket.getLength());
                i--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null){

                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(datagramSocket != null){

                try {
                    datagramSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
