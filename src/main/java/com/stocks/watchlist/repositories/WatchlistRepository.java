package com.stocks.watchlist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.watchlist.models.Watchlist;
@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist,Long>{

	List<Watchlist> findByOwnerId(Long id);
	
}
