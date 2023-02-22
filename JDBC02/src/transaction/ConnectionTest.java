package transaction;

import org.junit.Test;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author MikeCoder
 * @create 2023-02-1715:17
 * @description:transaction
 * @verson:
 */
public class ConnectionTest {
    @Test
    public void testGetConnection() {
        Connection conn = null;
        try {
            String sql = "select count(*) from user_table where 1 = ?";
            conn = JdbcUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
