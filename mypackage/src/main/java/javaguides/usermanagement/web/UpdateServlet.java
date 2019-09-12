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

@WebServlet("/adminedit")
public class UpdateServlet extends HttpServlet {

    //UserServiceImpl userServiceImpl = new UserServiceImpl();
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String newname = request.getParameter("name");
        int newage = Integer.parseInt(request.getParameter("age"));
        String role = request.getParameter("role");
        userServiceImpl.updateUser(id,newname,newage, role);
        response.sendRedirect("admin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = null;
        try {
            existingUser = userServiceImpl.selectUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

}
