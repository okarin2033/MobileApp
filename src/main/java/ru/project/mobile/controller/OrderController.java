package ru.project.mobile.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.OrderDto;
import ru.project.mobile.entity.ClientOrder;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.ClientOrderRepo;
import ru.project.mobile.service.Authorisation.TokenService;
import ru.project.mobile.service.OrderService;

import java.util.ArrayList;
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
    public boolean addOrder(@RequestHeader String token, @RequestBody OrderDto orderDto) {
        try {
            User user = tokenService.getUserByToken(token);
            ClientOrder order = orderService.compactOrder(orderDto);
            order.setUser(user);
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("token expired");
        }

    }
    @GetMapping("/get")
    public List<OrderDto> getClientOrderList(@RequestHeader String token){
        try {
            User user = tokenService.getUserByToken(token);
            List<ClientOrder> clientOrderList = orderRepository.getClientOrdersByUser(user);
            List<OrderDto> toSendList = new ArrayList<>();
            clientOrderList.forEach(order ->{
                OrderDto dto = new OrderDto(order.getBookList(), order.getFullPrice());
                toSendList.add(dto);
            });
            return toSendList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
