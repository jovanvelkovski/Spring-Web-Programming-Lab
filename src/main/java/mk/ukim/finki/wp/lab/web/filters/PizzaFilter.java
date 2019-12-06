package mk.ukim.finki.wp.lab.web.filters;

import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter
public class PizzaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        HttpSession session = req.getSession();
        String path = req.getServletPath();
        Order o = (Order)session.getAttribute("order");
        String pizzaType;

        if(o==null){
            pizzaType = null;
        }
        else{
            pizzaType = o.getPizzaType();
        }

        if (!"".equals(path) && (pizzaType == null || pizzaType.isEmpty())) {
            session.setAttribute("message", "Select a pizza!");
            resp.sendRedirect("");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}