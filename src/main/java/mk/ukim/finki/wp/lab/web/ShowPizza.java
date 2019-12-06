package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.Pizza;
import mk.ukim.finki.wp.lab.service.PizzaService;
import mk.ukim.finki.wp.lab.service.impl.PizzaServiceimpl;
import org.springframework.web.servlet.support.WebContentGenerator;
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

@WebServlet(name="show-pizza-servlet", urlPatterns = "")
public class ShowPizza extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final PizzaServiceimpl pizzaServiceimpl;

    public ShowPizza(SpringTemplateEngine springTemplateEngine, PizzaServiceimpl pizzaServiceimpl) {
        this.springTemplateEngine = springTemplateEngine;
        this.pizzaServiceimpl = pizzaServiceimpl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        List<Pizza> pizzas = pizzaServiceimpl.listPizzas();

//        String pizzaType = session.getAttribute("newPizzaType").toString();
//        String pizzaSize = session.getAttribute("newPizzaSize").toString();
//
//        if(pizzaType != null && pizzaSize != null){
//            pizzas.add(new Pizza(pizzaType, pizzaSize));
//        }

        webContext.setVariable("pizzas", pizzas);
        webContext.setVariable("message", req.getSession().getAttribute("message"));
        this.springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(req.getParameter("newPizzaName") != null && req.getParameter("newPizzaDescription") != null){
            String newPizzaName = req.getParameter("newPizzaName");
            String newPizzaDescription = req.getParameter("newPizzaDescription");
            pizzaServiceimpl.addPizza(newPizzaName, newPizzaDescription);
        }


        Order order = new Order();
        order.setPizzaType(req.getParameter("pizza"));
        //session.setAttribute("pizza", req.getParameter("pizza"));
        session.setAttribute("order", order);
        resp.sendRedirect("/selectPizza.do");
    }
}