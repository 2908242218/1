package transaction;

/**
 * @author MikeCoder
 * @create 2023-02-1715:22
 * @description:transaction
 * @verson:
 */

import org.junit.Test;
import util.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 1.什么叫数据库事务？
 * 事务：一组逻辑操作单元，使数据从一种状态变换到另一种状态
 *          >一组逻辑操作单元，一个或多个DML操作
 * 2.事务处理的原则：保证所有事务都作为一个工作单元来执行，即使出现故障，都不能改变这种执行方式。
 * 当再一个事务中执行多个操作时，要么所有事务都被提交（commit），那么这些修改就永久地保存下来；
 * 要么数据库管理系统将放弃所作地所有修改，整个事务回滚（rollback）到最初状态
 *
 * 3.数据一旦提交，就不可回滚
 *
 * 4.哪些操作会导致数据地自动提交？
 *          >DDL操作一旦执行，都会自动提交。
 *              >set autocommit = false 对DDL操作失效
 *          >DML默认情况下，一旦执行，就会自动提交。
 *              >我们可以通过set autocommit = false的方式取消DML操作的自动提交。
 *          >默认在关闭连接时，会自动提交数据。
 * 5.事务的ACID属性：
 *          >1.原子性(Atomicity)：原子性是指事务是一个不可分割的工作单元，事务中的操作要么都发生，要么都不发生。
 *          >2.一致性(Consistency):事务必须使用数据库从一个一致性状态变换到另一个一致性状态。
 *          >3.隔离性(Isolation):事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作的操作
 *          及其使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能相互干扰
 *          >4.持久性(Durability)：持久性是指一个事务一旦被提交，它对数据库中数据的改变就是持久性的，接下来的
 *          其他操作和数据库故障不应该对其有任何影响。
 *  6.事务几种并发问题：
 *          >脏读：对于两个事务T1,T2，T1读取了已经被T2更新但没有被提交的字段，之后，若T2回滚，T1读取
 *          的内容就是临时且无效的。
 *          >不可重复读：对于两个事务T1,T2，T1读取了一个字段，然后T2更新了字段，之后,T1再次读取同一个
 *          字段，值就不同了。
 *          >幻读：对于两个事务T1,T2,T1从一个表中读取了一个字段，然后T2在该表中插入了一些新的行，之后，
 *          如果T1再次读取同一个表，就会读出几行。
 *          四种隔离级别：
 *                 >PEAD UNCOMMITED(读取未被提交数据）：三种问题都可能出现。
 *                 >PEAD COMMITED(读已提交数据）：解决了脏读问题。
 *                 >PEPEATABLE READ（可重复读）：解决了脏读和不可重复读两个问题。
 *                 >SERIALIZABLE（传行化）：解决了三个问题。
 */
public class TransactionTest {
    //事务隔离性（isolation)测试
    @Test
    public void testIsolationQuery() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            System.out.println(conn.getTransactionIsolation());
            conn.setTransactionIsolation(4);
            conn.setAutoCommit(false);
            String sql = "select user,password,balance from user_table where user = ?";
            User cc = getInstance(conn, User.class, sql, "CC");
            System.out.println(cc);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null)
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
       } //finally {
//            try {
//                if(conn != null)
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
 //       }
    }
    @Test
    public void testIsolationUpate()  {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "update user_table set balance = ?";
            update(conn,sql,6000);
            Thread.sleep(15000);
            System.out.println("修改成功");
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null)
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 当考虑到数据库事务后，进行的转账操作。
     */
    @Test
    public void testTransferAccounts()  {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            //1.取消数据的自动提交
            conn.setAutoCommit(false);
            String sql = "update user_table set balance =balance-100 where user = ?";
            update(conn,sql,"AA");
//            System.out.println("网络故障");
//            System.out.println(10/0);
            sql = "update user_table set balance = balance+100 where user = ?";
            update(conn,sql,"BB");
            System.out.println("转账成功");
            //2.提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //3.数据回滚
                conn.rollback();
                System.out.println("转账失败");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            //4.修改其为自动提交数据
            //主要针对于使用数据库连接池的使用
            try {
                if(conn != null)
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

   //针对于表的通用的增删改操作2.0
    public static int update(Connection conn,String sql,Object ...args) {
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
    /**
     * 根据查询的不同表，返回不同对象,考虑到事务后
     * @param <T>
     * @return
     */
    public static <T> T getInstance(Connection conn,Class<T> clazz,String sql,Object...args) {
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

}
