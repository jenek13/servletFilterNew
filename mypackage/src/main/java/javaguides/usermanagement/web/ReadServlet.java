package javaguides.usermanagement.web;

import javaguides.usermanagement.service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class ReadServlet extends HttpServlet {


        //UserServiceImpl userServiceImpl = new UserServiceImpl();
       UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=utf-8");
                request.setAttribute("listUser", userServiceImpl.listUser() );
                RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
                dispatcher.forward(request, response);
            }
}
