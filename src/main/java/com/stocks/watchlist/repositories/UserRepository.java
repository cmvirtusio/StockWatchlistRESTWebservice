package com.stocks.watchlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stocks.watchlist.models.User;

/**
 * User repository for CRUD operations.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
