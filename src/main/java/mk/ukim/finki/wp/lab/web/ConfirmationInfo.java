package mk.ukim.finki.wp.lab.web;

import eu.bitwalker.useragentutils.UserAgent;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.service.OrderService;
import mk.ukim.finki.wp.lab.service.impl.OrderServiceimpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "confirmation-info-servlet", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderServiceimpl orderServiceimpl;

    public ConfirmationInfo(SpringTemplateEngine springTemplateEngine, OrderServiceimpl orderServiceimpl) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderServiceimpl = orderServiceimpl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        webContext.setVariable("pizzaType", order.getPizzaType());
        webContext.setVariable("pizzaSize", order.getPizzaSize());
        webContext.setVariable("clientName", order.getClientName());
        webContext.setVariable("clientAddress", order.getClientAddress());
        webContext.setVariable("clientIpAddress", req.getRemoteHost());

        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        System.out.println(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
        String browserName = userAgent.getBrowser().getName()+" version: "+userAgent.getBrowserVersion();

        webContext.setVariable("clientBrowser", browserName);
        orderServiceimpl.placeOrder(order.getPizzaType(), order.getPizzaSize(), order.getClientName(), order.getClientAddress());
        this.springTemplateEngine.process("/confirmationInfo.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("");
    }
}