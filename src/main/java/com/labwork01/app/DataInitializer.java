//package com.labwork01.app;
//
//import com.labwork01.app.bouquet.model.Bouquet;
//import com.labwork01.app.bouquet.repository.BouquetRepository;
//import com.labwork01.app.order.model.Order;
//import com.labwork01.app.order.repository.OrderRepository;
//import com.labwork01.app.user.model.User;
//import com.labwork01.app.user.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.List;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final BouquetRepository bouquetRepository;
//    private final UserRepository userRepository;
//    private final OrderRepository orderRepository;
//
//    public DataInitializer(BouquetRepository bouquetRepository, UserRepository userRepository, OrderRepository orderRepository) {
//        this.bouquetRepository = bouquetRepository;
//        this.userRepository = userRepository;
//        this.orderRepository = orderRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Добавление букетов
//        for (int i = 8; i <= 17; i++) {
//            byte[] image = Files.readAllBytes(Path.of("C:\\Users\\movavi\\Desktop\\images\\image" + i + ".png"));
//            Bouquet bouquet = new Bouquet("Букет " + i, 10, 500 * i, image);
//            bouquetRepository.save(bouquet);
//        }
//
//        // Добавление пользователей
//        User alina = new User("Alina", "1990-01-01", "1234567890", "123");
//        User denis = new User("Denis", "1990-01-01", "0987654321", "123");
//        userRepository.save(alina);
//        userRepository.save(denis);
//
//        // Добавление заказов
//        List<Bouquet> bouquets = bouquetRepository.findAll();
//        for (int i = 0; i < 4; i++) {
//            Order order = new Order("2023-12-20", 1000, bouquets.subList(i, i + 2), i % 2 == 0 ? alina : denis);
//            orderRepository.save(order);
//        }
//    }
//}
