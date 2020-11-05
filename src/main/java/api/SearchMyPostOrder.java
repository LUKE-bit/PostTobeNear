package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Order;
import model.OrderDao;
import model.User;
import util.PostException;
import util.PostUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchMyPostOrder")
public class SearchMyPostOrder extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Response{
        public String username;
        public List<Order> orders;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前端发一个userId
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset = utf-8");
        List<Order> orders = new ArrayList<>();
        Response response = new Response();
        try {
            HttpSession session = req.getSession(false);
            if (session==null) {
                throw new PostException("您尚未登录");
            }
            User user = (User) session.getAttribute("user");
            if (user==null) {
                throw new PostException("您尚未登陆");
            }
            OrderDao orderDao = new OrderDao();
            orders = orderDao.selectMyPostOrder(user.getUserId());
            response.orders = orders;
            response.username = user.getAccount();
        } catch (PostException e) {
            e.printStackTrace();
        } finally {
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}
