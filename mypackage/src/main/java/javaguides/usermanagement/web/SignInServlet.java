package javaguides.usermanagement.web;

import javaguides.usermanagement.model.User;
import javaguides.usermanagement.service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/signin")
@WebServlet("/")
public class SignInServlet extends HttpServlet {


    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("start-form.jsp");//сразу пернаправляет  на эту джспи при вызове этого мтода
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                User user = userServiceImpl.getUserbyLoginPassword(login,password);
                if(user == null) {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().println("Unauthorized");
                } else {
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("Authorized: " + login);
                    response.setStatus(HttpServletResponse.SC_OK);

                    String role = user.getRole();
                    request.getSession().setAttribute("role", role);
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("redirectFilter");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   }

}
