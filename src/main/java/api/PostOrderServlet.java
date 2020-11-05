package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Order;
import model.OrderDao;
import model.User;
import util.PostException;
import util.PostUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.util.List;


@WebServlet("/PostOrder")
public class PostOrderServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Request{
        public String description;
        public int money;
        public String type;
        public String time;
    }
    static class Response{
        public List<Order> orders;
        public int ok;
        public String message;
    }

    //新增订单
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new PostException("您尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                throw new PostException("您尚未登陆");
            }
            System.out.println(1111);
            String body = PostUtil.readBody(req);
            System.out.println(body);
            Request request = null;
            try{
                request = gson.fromJson(body,Request.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            Order order = new Order();
            order.setType(request.type);
            System.out.println(order.getType());
            order.setMoney(request.money);
            System.out.println(order.getMoney());
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String time = simpleDateFormat.format(request.time);
            order.setTime(Timestamp.valueOf(request.time));
            System.out.println(order.getTime());
            order.setPostId(user.getUserId());
            order.setDescription(request.description);
            System.out.println(order.getDescription());
            OrderDao orderDao = new OrderDao();
            orderDao.add(order,user.getUserId());
            System.out.println("成功");
            response.ok = 1;
            response.message = "";
        } catch (PostException postException) {
            response.ok = 0;
            response.message = postException.getMessage();
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
        resp.setContentType("application/json; charset=utf-8");
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
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }

}
