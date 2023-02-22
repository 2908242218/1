package dao;

import bean.Customer;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


/**
 * @author MikeCoder
 * @create 2023-02-1720:05
 * @description:
 * @verson:
 */
public class CustomerDAOImplTest {
    CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void insert() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Customer cust = new Customer(20, "大树", "dashu@126.com", new Date(5643558842L));
            dao.insert(conn, cust);
            System.out.println("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                if (conn != null)
                   DbUtils.closeQuietly(conn);
        }
    }

    @Test
    public void deleteById() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            dao.deleteById(conn, 19);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateById() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Customer cust = new Customer(18, "大树", "dashu@126.com", new Date(45662255L));
            dao.updateById(conn, 18, cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getCustomerById() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection1();
            Customer cust = dao.getCustomerById(conn, 18);
            System.out.println(cust);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getAll() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Customer> list = dao.getAll(conn);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getCount() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Long count = dao.getCount(conn);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getMaxBirth() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println(maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}