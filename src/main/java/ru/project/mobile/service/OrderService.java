package ru.project.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.mobile.dto.OrderDto;
import ru.project.mobile.entity.ClientOrder;
import ru.project.mobile.entity.User;
import ru.project.mobile.repository.UserRepo;

@Service
public class OrderService {

    @Autowired
    UserRepo userRepo;

    public ClientOrder compactOrder(OrderDto orderDto){
        ClientOrder order = new ClientOrder();
        order.setFullPrice(orderDto.getFullPrice());
        order.setBookList(orderDto.getBookList());
        return order;
    }


}
