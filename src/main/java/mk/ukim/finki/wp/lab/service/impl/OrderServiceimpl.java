package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.repository.OrderRepository;
import mk.ukim.finki.wp.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceimpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceimpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address) {
        Order o = new Order();
        o.setPizzaType(pizzaType);
        o.setPizzaSize(pizzaSize);
        o.setClientName(clientName);
        o.setClientAddress(address);

        this.orderRepository.placeOrder(o);

        return o;
    }


}