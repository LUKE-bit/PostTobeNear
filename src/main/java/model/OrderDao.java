package model;

import util.PostException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 操作订单
// 1. 新增订单
// 2. 接受订单
// 3. 查看所有订单
// 4. 修改订单状态(订单是否已经完成)
// 5.查看我的订单
// 6.查看我的接单
public class OrderDao {
    // 新增订单
    public void add(Order order,int postId) throws PostException {
        addOrder(order,postId);
    }

    public void take(int orderId) throws PostException{
        deleteOrder(orderId);
    }
    //新增订单
    private void addOrder(Order order,int postId) throws PostException {
        // 1. 先获取到数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 构造 SQL
        String sql = "insert into orders values(null,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, order.getType());
            statement.setInt(2, order.getMoney());
            statement.setTimestamp(3, order.getTime());
            statement.setString(4, order.getDescription());
            statement.setInt(5, 0);
            statement.setInt(6,postId);
            statement.setInt(7,order.getTakeId());
            // 3. 执行 SQL
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new PostException("插入订单失败");
            }
            System.out.println("插入订单第一步成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("插入订单失败");
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }

    public void deleteOrder(int orderId) throws PostException {
        //1.先获取到数据库连接
        Connection connection = DBUtil.getConnection();
        //2.构造sql请求
        String sql = "delete from orders where orderId = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            //3.执行sql
            int ret = statement.executeUpdate();
            if (ret!=1) {
                throw new PostException("接单失败");
            }
            System.out.println("接单成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,null);
        }
    }

    // 获取到所有的订单信息
    public List<Order> selectAll() {
        List<Order> orders = new ArrayList<>();
        // 1. 获取到数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        String sql = "select * from orders where isTake = 0";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            // 3. 直接执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setType(resultSet.getString("type"));
                order.setMoney(resultSet.getInt("money"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setDescription(resultSet.getString("description"));
                order.setIsTake(resultSet.getInt("isTake"));
                order.setPostId(resultSet.getInt("postId"));
                order.setTakeId(resultSet.getInt("takeId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 断开连接
            DBUtil.close(connection, statement, resultSet);
        }
        return orders;
    }
    //接单
    public void changeState(int orderId, int isTake,int takeId) throws PostException {
        Connection connection = DBUtil.getConnection();
        String sql = "update orders set isTake = ?,set takeId = ? where orderId = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, isTake);
            statement.setInt(2, takeId);
            statement.setInt(3,orderId);
            int ret = statement.executeUpdate();
            if (ret != 1) {
                throw new PostException("修改订单状态失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("修改订单状态失败");
        } finally {
            DBUtil.close(connection, statement, null);
        }
    }
    public void ensureOrder(int orderId,int isTake) throws PostException {
        Connection connection = DBUtil.getConnection();
        String sql = "update orders set isTake = ? where orderId = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,isTake);
            ps.setInt(2,orderId);
            int ret = ps.executeUpdate();
            if (ret!=1) {
                throw new PostException("修改订单状态失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("修改订单状态失败");
        } finally {
            DBUtil.close(connection,ps,null);
        }

    }
    public List<Order> selectMyPostOrder(int userId) throws PostException{
        List<Order> orders = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String sql = "select * from orders where postId = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setType(resultSet.getString("type"));
                order.setMoney(resultSet.getInt("money"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setDescription(resultSet.getString("description"));
                order.setIsTake(resultSet.getInt("isTake"));
                order.setTakeId(resultSet.getInt("takeId"));
                order.setPostId(resultSet.getInt("postId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("查看发单失败");
        } finally {
            DBUtil.close(connection,ps,null);
        }
        return orders;
    }
    public List<Order> selectMyTakeOrder(int userId) throws PostException{
        List<Order> orders = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        String sql = "select * from orders where takeId = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setType(resultSet.getString("type"));
                order.setMoney(resultSet.getInt("money"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setDescription(resultSet.getString("description"));
                order.setIsTake(resultSet.getInt("isTake"));
                order.setTakeId(resultSet.getInt("takeId"));
                order.setPostId(resultSet.getInt("postId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PostException("查看发单失败");
        } finally {
            DBUtil.close(connection,ps,null);
        }
        return orders;
    }
}