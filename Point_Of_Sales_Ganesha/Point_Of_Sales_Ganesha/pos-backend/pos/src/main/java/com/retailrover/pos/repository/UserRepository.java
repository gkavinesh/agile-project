package com.retailrover.pos.repository;

import com.retailrover.pos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}
