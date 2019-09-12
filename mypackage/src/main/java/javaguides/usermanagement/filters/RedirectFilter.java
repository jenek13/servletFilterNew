package javaguides.usermanagement.filters;

import javaguides.usermanagement.model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/filter")
@WebFilter(filterName="redirectFilter", urlPatterns="/redirectFilter")
public class RedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        session.setAttribute("user", user);

        String role = user.getRole();

        //String role = String.valueOf(session.getAttribute("role"));
        if (role.equals("admin")) {
            res.sendRedirect("admin");
        } else if(role.equals("user")) {
            res.sendRedirect("user");
        }else {
            res.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
