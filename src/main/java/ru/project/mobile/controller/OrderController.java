package ru.project.mobile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.OrderDto;
import ru.project.mobile.entity.ClientOrder;
import ru.project.mobile.entity.User;
import ru.project.mobile.entity.UserToken;
import ru.project.mobile.repository.ClientOrderRepository;
import ru.project.mobile.service.Authorisation.TokenService;
import ru.project.mobile.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ClientOrderRepository orderRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    OrderService orderService;

    @PostMapping("/new")
    public ClientOrder addOrder(@RequestHeader String token, @RequestBody OrderDto orderDto) {
        try {
            User user = tokenService.getUserByToken(token);
            ClientOrder order = orderService.compactOrder(orderDto);
            order.setUser(user);
            orderRepository.save(order);
            return order;

        } catch (Exception e) {
            throw new RuntimeException("token expired");
        }

    }
}
