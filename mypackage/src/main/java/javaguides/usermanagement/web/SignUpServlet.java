package javaguides.usermanagement.web;

import javaguides.usermanagement.service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        String login = request.getParameter("login");
        String pass  = request.getParameter("password");
        String name  = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String role = request.getParameter("role");
        if (login  == null || pass  == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            userServiceImpl.insertUserByLogin(login, pass, name, age, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register-form.jsp");
        dispatcher.forward(request, response);
    }
}
