package ru.project.mobile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.OrderDto;
import ru.project.mobile.entity.ClientOrder;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.ClientOrderRepo;
import ru.project.mobile.service.Authorisation.TokenService;
import ru.project.mobile.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ClientOrderRepo orderRepository;
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
    @PostMapping("/get")
    public List<ClientOrder> getClientOrederList(@RequestHeader String token){
        try {
            User user = tokenService.getUserByToken(token);
            return orderRepository.getClientOrdersByUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
