package com.sk.sb.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.sb.code.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);

}
