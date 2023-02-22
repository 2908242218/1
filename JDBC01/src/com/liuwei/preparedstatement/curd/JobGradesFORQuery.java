package com.liuwei.preparedstatement.curd;

import com.liuwei.bean.JobGrades;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author MikeCoder
 * @create 2023-02-0520:26
 * @description:curd
 * @verson:
 */
public class JobGradesFORQuery {
    @Test
    public void testjodGradesCommonFORQuery(){
        String sql = "select grade_level gradeLevel,lowest_sal lowestSal,highest_sal highestSal from job_grades where grade_level = ?";
        JobGrades jobGrades = jodGradesCommonFORQuery(sql,"A");
        System.out.println(jobGrades);
    }

    public JobGrades jodGradesCommonFORQuery(String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //生成预编译PrepareStatement对象
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行，获得ResultSet结果集
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();//获取结果集的元数据
            int columnCount = rsmd.getColumnCount();//获取结果集列数
            if(rs.next()){
                JobGrades jobGrades = new JobGrades();
                for(int i = 0;i<columnCount;i++){
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //获取列别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //将columnValue和columnLable分别付给对象的对应属性
                    Field field = JobGrades.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(jobGrades,columnValue);
                }
                return jobGrades;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(conn,ps,rs);
        }
        return null;

    }
}
