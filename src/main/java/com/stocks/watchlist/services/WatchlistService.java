package com.stocks.watchlist.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return watchlistRepository.findById(id).isPresent();
	}
}
