package com.stocks.watchlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.watchlist.models.Watchlist;
@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long>{
	
}
