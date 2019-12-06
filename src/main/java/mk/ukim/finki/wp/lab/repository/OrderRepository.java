package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order){
        Random r = new Random();
        order.setOrderId(r.nextLong());
        orders.add(order);
    }
}