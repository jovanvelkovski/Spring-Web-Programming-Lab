package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.impl.PizzaServiceimpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "pizza-size-servlet", urlPatterns = "/selectPizza.do")
public class SelectPizza extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public SelectPizza(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        String pizzaType = order.getPizzaType();
        //order.setPizzaType(session.getAttribute("pizza").toString());
        webContext.setVariable("pizzaType", order.getPizzaType());
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order");
        order.setPizzaSize(req.getParameter("pizza_size"));
        //session.setAttribute("order", order);

        resp.sendRedirect("/PizzaOrder.do");
    }
}