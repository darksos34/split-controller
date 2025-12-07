package com.jda.coder.splitcontroller.repository;

import com.jda.coder.splitcontroller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
