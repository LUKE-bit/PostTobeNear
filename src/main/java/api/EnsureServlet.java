package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@WebServlet("/ensure")
public class EnsureServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Request{
        public int orderId;
        public int isTake;
    }
    static class Response {
        public int ok;
        public String reason;
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response = new Response();
        try {
            HttpSession session = req.getSession(false);
            if (session==null){
                throw new PostException("您当前未登录");
            }
            User user = (User) session.getAttribute("user");
            String body = PostUtil.readBody(req);
            Request request = gson.fromJson(body,Request.class);
            OrderDao orderDao = new OrderDao();
            orderDao.ensureOrder(request.orderId,request.isTake);
            response.ok = 1;
            response.reason = "";
        } catch (PostException e) {
            response.ok = 0;
            response.reason = e.getMessage();
        }finally {
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}
