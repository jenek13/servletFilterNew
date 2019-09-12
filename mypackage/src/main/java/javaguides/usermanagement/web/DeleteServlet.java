package javaguides.usermanagement.web;

import javaguides.usermanagement.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admindelete")
public class DeleteServlet extends HttpServlet {

   // UserServiceImpl userServiceImpl = new UserServiceImpl();
   UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            userServiceImpl.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin");
    }

}
