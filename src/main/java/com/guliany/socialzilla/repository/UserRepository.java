package com.guliany.socialzilla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guliany.socialzilla.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
