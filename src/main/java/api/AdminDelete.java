package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Order;
import model.OrderDao;
import model.User;
import util.PostException;
import util.PostUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminDelete extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Request{
        int orderId;
    }
    static class Response {
        int ok;
        List<Order> orders;
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = PostUtil.readBody(req);
        Response response = new Response();
        Request request = gson.fromJson(body,Request.class);
        OrderDao orderDao = new OrderDao();
        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                response.ok=0;
                throw new PostException("您尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.ok=0;
                throw new PostException("您尚未登陆");
            }
//            if(user.getIsAdmin==0) {
//                response.ok = 0;
//                throw new PostException("您不是管理员");
//            }
            orderDao.deleteOrder(request.orderId);
            response.ok = 1;
        } catch (PostException e) {
            response.ok = 0;
            e.printStackTrace();
        }finally {
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }

    }

    //查看所有订单
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        List<Order> orders;
        Response response = new Response();
        try {
            //1. 验证用户登陆状态.
            HttpSession session = req.getSession(false);
            if (session == null) {
                response.ok=0;
                throw new PostException("您尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.ok=0;
                throw new PostException("您尚未登陆");
            }
            OrderDao orderDao = new OrderDao();
            // 2. 查找数据库, 查找所有订单
            orders = orderDao.selectAll();
            response.ok=1;
            response.orders = orders;
        } catch (PostException e) {
            e.printStackTrace();
        }finally {
            // 3. 构造响应结果
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}
