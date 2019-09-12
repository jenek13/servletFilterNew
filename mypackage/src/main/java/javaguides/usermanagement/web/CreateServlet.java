package javaguides.usermanagement.web;

import javaguides.usermanagement.model.User;
import javaguides.usermanagement.service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javaguides.usermanagement.dao.UserDaoFactory;
import javaguides.usermanagement.service.UserServiceImpl;

@WebServlet("/adminnew")
public class CreateServlet extends HttpServlet {

    //private UserHibernateServiceImpl userHibernateServiceImpl = new UserHibernateServiceImpl();
    //UserService<User> userUserService = new UserHibernateServiceImpl();

   // UserServiceImpl userServiceImpl = new UserServiceImpl();
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    //UserDAO userDAO = getUserDAO("jdbc");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
            request.setCharacterEncoding("utf-8");
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String role = request.getParameter("role");
            try {
                userServiceImpl.insertUser(name, age, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
                dispatcher.forward(request, response);
    }
}
