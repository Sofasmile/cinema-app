package cinema.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cinema.dao.OrderDao;
import cinema.lib.Inject;
import cinema.lib.Service;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        List<Ticket> tickets = shoppingCart.getTickets();
        User user = shoppingCart.getUser();
        Order order = new Order();
        order.setTickets(new ArrayList<>(tickets));
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        shoppingCartService.clearShoppingCart(shoppingCart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersByUser(user);
    }
}
