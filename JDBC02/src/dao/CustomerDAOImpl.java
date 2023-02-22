package dao;

import bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author MikeCoder
 * @create 2023-02-1719:27
 * @description:dao
 * @verson:
 */
public class CustomerDAOImpl extends DAO<Customer> implements CustomerDAO{
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(id,name,email,birth) values(?,?,?,?)";
       // update(conn,sql,cust.getId(),cust.getName(),cust.getEmail(),cust.getBirth());
        QueryRunner runner = new QueryRunner();
        BeanHandler<Customer> rsh = new BeanHandler<>(Customer.class);
        try {
            runner.insert(conn,sql,rsh,cust.getId(),cust.getName(),cust.getEmail(),cust.getBirth());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Connection conn, int id) throws SQLException {
        String sql = "delete from customers where id = ?";
        //update(conn,sql,id);
        QueryRunner runner = new QueryRunner();
        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
        Customer customer = runner.query(conn, sql, handler, id);
    }

    @Override
    public void updateById(Connection conn, int id, Customer cust) {
        String sql = "update customers set id = ?,name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,cust.getId(),cust.getName(),cust.getEmail(),cust.getBirth(),id);
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer customer = getInstance(conn, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        List<Customer> list = getFORList(conn, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        Long value =  getValue(conn, sql);
        return value;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        Date maxBrith = getValue(conn, sql);
        return maxBrith;
    }
}
