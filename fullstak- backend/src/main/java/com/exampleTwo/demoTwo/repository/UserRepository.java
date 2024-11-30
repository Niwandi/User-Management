package com.exampleTwo.demoTwo.repository;

import com.exampleTwo.demoTwo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
