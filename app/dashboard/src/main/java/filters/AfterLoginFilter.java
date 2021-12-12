package filters;

import pl.rwalski.managed.beans.SessionsContainer;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {"/*"})
public class AfterLoginFilter implements Filter{

    private static final Logger logger = Logger.getLogger(AfterLoginFilter.class.toString());

    @Inject
    private SessionsContainer sessionsContainer;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String remoteUser = httpRequest.getRemoteUser();

        if (remoteUser != null && httpRequest.getSession().getAttribute("user") == null) {
            logger.info("" + sessionsContainer.size());
            if (sessionsContainer.contains(remoteUser)) {
                logger.info("User " + remoteUser + " already logged in, redirecting to error page");
                httpRequest.getSession().invalidate();
                ((HttpServletResponse)servletResponse).sendRedirect("/dashboard/userLoggedInError.xhtml?faces-redirect=true");
            } else {
                logger.info("User " + remoteUser + " logged in");
                httpRequest.getSession().setAttribute("user", true);
                sessionsContainer.add(remoteUser);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
