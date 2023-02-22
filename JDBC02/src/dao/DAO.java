package dao;

import util.JdbcUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MikeCoder
 * @create 2023-02-1717:56
 * @description:dao
 * @verson:
 * 封装针对于表的通用操作
 */
public abstract class DAO<T> {
       private Class<T> clazz = null;
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paraType = (ParameterizedType) genericSuperclass;
        Type[] types = paraType.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    //针对于表的通用的增删改操作2.0
    public  int update(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //3.执行操作
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(null,ps);
        }
        return 0;
    }
    //针对不同的表，返回一个对象，考虑到数据库的事务后
    public  T getInstance(Connection conn,String sql,Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行，产生结果集
            rs = ps.executeQuery();
            //获得结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获得结果集列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0;i<columnCount;i++){
                    //获取列的值
                    Object columnValue = rs.getObject(i + 1);
                    //获取列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //将获取到的值付给对象与别名一致的属性
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(null,ps,rs);
        }
        return null;
    }
    //根据不同的表，返回对象集合，考虑到数据库的事务后
    public List<T> getFORList(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //预编译
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行获得结果集中
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            //为查询到的每一个字段创建对象并为属性赋值
            while(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0;i<columnCount;i++){
                    //获得列值
                    Object columnValue = rs.getObject(i + 1);
                    //获得列别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //为属性赋值
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(null,ps,rs);
        }
        return null;
    }
    //查询某个值的通用操作
    public  <E> E getValue(Connection conn,String sql,Object...args)  {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //生成预编译对象
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行
            rs = ps.executeQuery();
            if(rs.next()){
               return  (E)rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(null,ps,rs);
        }
        return null;
    }
}
