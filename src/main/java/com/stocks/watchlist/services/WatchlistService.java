package com.stocks.watchlist.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocks.watchlist.models.User;
import com.stocks.watchlist.models.Watchlist;
import com.stocks.watchlist.repositories.WatchlistRepository;

@Service
public class WatchlistService {
	//Business Logic
	@Autowired
	private WatchlistRepository watchlistRepository;
	
	public List<Watchlist> getAllWatchlists(){
		return watchlistRepository.findAll();
	}

	public void insert(Watchlist wl) {
		// TODO Auto-generated method stub
		watchlistRepository.save(wl);
	}

	public void updateWatchlist(long id, Watchlist wl) {
		// TODO Auto-generated method stub
		watchlistRepository.save(wl);
	}

	public void deleteWatchlist(long id) {
		// TODO Auto-generated method stub
		watchlistRepository.deleteById(id);
	}

	public boolean idExists(long id) {
		// TODO Auto-generated method stub
		return watchlistRepository.existsById(id);
	}
	
	public Watchlist owner(long id) {
		return watchlistRepository.findById(id).orElse(null);
	}

	public List<Watchlist> findByUser(User user) {
		//Implement findByOwnerId because OwnerId is a foreignkey
		return watchlistRepository.findByOwnerId(user.getId());
	}
}
