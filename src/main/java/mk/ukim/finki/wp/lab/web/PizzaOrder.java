package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "pizza-order-servlet", urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        webContext.setVariable("pizzaType", order.getPizzaType());
        webContext.setVariable("pizzaSize", order.getPizzaSize());
        this.springTemplateEngine.process("/deliveryInfo.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        order.setClientName(req.getParameter("clientName"));
        order.setClientAddress(req.getParameter("clientAddress"));
        resp.sendRedirect("/ConfirmationInfo.do");
    }

}