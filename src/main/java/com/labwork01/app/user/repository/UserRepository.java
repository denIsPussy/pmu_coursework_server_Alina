package com.labwork01.app.user.repository;

import com.labwork01.app.order.repository.OrderRepositoryExtension;
import com.labwork01.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryExtension {
}
