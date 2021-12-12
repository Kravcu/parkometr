package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {"/login.xhtml"})
public class UserLoggedInFilter implements Filter {

    private static final Logger logger = Logger.getLogger(UserLoggedInFilter.class.toString());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        ((HttpServletResponse)servletResponse).sendRedirect("/dashboard/restricted/index.xhtml?faces-redirect=true");


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
