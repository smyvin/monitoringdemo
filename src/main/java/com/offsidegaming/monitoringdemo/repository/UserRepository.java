package com.offsidegaming.monitoringdemo.repository;

import com.offsidegaming.monitoringdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
