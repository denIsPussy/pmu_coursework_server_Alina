package com.labwork01.app.user.service;

import com.labwork01.app.order.model.Order;
import com.labwork01.app.order.repository.OrderRepository;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserDTO;
import com.labwork01.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
    @Transactional
    public User insert(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }
    @Transactional
    public User delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }
    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }
    @Transactional
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    @Transactional
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public User update(UserDTO userDTO) {
        User user = findById(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }
    @Transactional
    public User removeOrdersFromUser(User user, List<Order> orders) {
        orders.forEach(
                order-> {
                    Order foundOrder = orderRepository.findById(order.getId()).orElse(null);
                    if (foundOrder != null) user.removeOrder(foundOrder);
                }
        );
        return user;
    }
}
