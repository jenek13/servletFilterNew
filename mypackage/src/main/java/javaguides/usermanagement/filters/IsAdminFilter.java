package javaguides.usermanagement.filters;

import javaguides.usermanagement.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//должен проверить админ это или нет

@WebFilter(filterName="isAdminFilter", urlPatterns="/isAdminFilter")
public class IsAdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        if (role.equals("admin")) {
            filterChain.doFilter(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().println("Unauthorized");
        }
    }

    @Override
    public void destroy() {

    }
}
