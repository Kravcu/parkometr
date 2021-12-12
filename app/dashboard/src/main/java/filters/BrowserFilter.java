package filters;

import com.google.common.net.HttpHeaders;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {"/restricted/*"})
public class BrowserFilter implements Filter {

    private static final Logger logger = Logger.getLogger(BrowserFilter.class.toString());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String userAgent = ((HttpServletRequest)servletRequest).getHeader(HttpHeaders.USER_AGENT);
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        if (shouldReject(userAgent)) {
            logger.info("BLOCKING - user_agent:" + userAgent);

            session.invalidate();
            ((HttpServletResponse)servletResponse).sendRedirect("/dashboard/error.xhtml?faces-redirect=true");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


    private boolean shouldReject(String userAgent) {
        return !userAgent.toLowerCase().contains("chrome");
    }
}
