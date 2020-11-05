package model;
import util.PostException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDao {
    public void add(User user) throws PostException{
        // 1. 先获取和数据库的连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL 语句
        String sql = "insert into User values(null, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getAccount());
            statement.setString(2, user.getPassword());
            // 3. 执行 SQL 语句(executeQuery, executeUpdate)
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new PostException("插入用户失败");
            }
            System.out.println("插入用户成功!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("插入用户失败");
        } finally {
            // 4. 关闭连接(close) (如果是查询语句, 还需要遍历结果集合)
            DBUtil.close(connection, statement, null);
        }
    }

    public User selectByAccount(String account) throws PostException {
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        String sql = "select * from User where account = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, account);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集(按照名字查找, 只能查到一个记录, 要求名字不能重复)
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("按账户查找用户失败");
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public User selectById(int userId) throws PostException {
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        String sql = "select * from User where userId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集, userId 是主键. 不会重复的. 最多只能查到一条记录
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("按 id 查找用户失败");
        } finally {
            // 5. 断开连接
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }
}