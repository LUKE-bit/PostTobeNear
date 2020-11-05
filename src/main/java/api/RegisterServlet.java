package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import model.UserDao;
import util.PostException;
import util.PostUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    static class Request {
        public String username;
        public String password;
    }
    static class Response{
        public int ok;//0失败
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response = new Response();
        String body = PostUtil.readBody(req);
        Request request = gson.fromJson(body,Request.class);
        System.out.println(request.username);
        if (request.username == null || "".equals(request.username) || request.password == null || "".equals(request.password)) {
            response.ok = 0;
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
            return;
        }
        System.out.println(1);
        try {
            UserDao userDao = new UserDao();
            User existUser = userDao.selectByAccount(request.username);
            if (existUser!=null) {
                System.out.println("somebody");
                response.ok = 0;
                return;
            }
            User user = new User();
            user.setAccount(request.username);
            user.setPassword(request.password);
            userDao.add(user);
            response.ok=1;
        } catch (PostException e) {
            e.printStackTrace();
        }finally {
            String jsonString = gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }
}
