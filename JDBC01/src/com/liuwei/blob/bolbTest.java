package com.liuwei.blob;

import com.liuwei.bean.Student;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author MikeCoder
 * @create 2023-02-0922:06
 * @description:blob 测试使用PrepareStatment操作Blob类型的数据
 * @verson:
 */
public class bolbTest {
    //向数据表examstudent数据表中插入Blob类型的字段
    @Test
    public void testInsert() throws Exception{
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade,Photo) values(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,4);
        ps.setObject(2,"513701200007033117");
        ps.setObject(3,"512019");
        ps.setObject(4,"大树王子1");
        ps.setObject(5,"巴中");
        ps.setObject(6,100);
        FileInputStream is = new FileInputStream(new File("DSC04326.jpg"));
        ps.setBlob(7,is);
        ps.execute();
        JdbcUtil.closeResource(conn,ps);
    }

    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream bs = null;
        FileOutputStream fos = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from examstudent where FlowID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,6);

            rs = ps.executeQuery();
            if(rs.next()){
                int flowID =rs.getInt(1);
                int type = rs.getInt(2);
                String idCard = rs.getString(3);
                String examCard = rs.getString(4);
                String studentName = rs.getString(5);
                String location = rs.getString(6);
                int grade = rs.getInt(7);
                Student student = new Student(flowID, type, idCard, examCard, studentName, location, grade);

                System.out.println(student);
            }
            //查询Blob类型数据
            Blob photo = rs.getBlob("Photo");
            bs = photo.getBinaryStream();
            fos = new FileOutputStream("刘桑的照片.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while((len = bs.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bs != null)
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JdbcUtil.closeResource(conn,ps,rs);
        }

    }
    @Test
    public void test(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from examstudent where FlowID = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,6);

            rs = ps.executeQuery();
            String  flowID =rs.getString("idCard");
            System.out.println(flowID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps,rs);
        }
    }
}
