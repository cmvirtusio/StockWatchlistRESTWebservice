package com.stocks.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.watchlist.models.Watchlist;
import com.stocks.watchlist.services.WatchlistService;

@RestController
public class WatchlistController {
	@Autowired
	private WatchlistService watchlistService;
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@PostMapping(value = "/createwatchlist")
	public String createWatchlist(@RequestBody Watchlist wl){
		if(wl.getTickers() == null) {
			wl.setTickers(new ArrayList<String>());
		}
		watchlistService.insert(wl);
		return "Watchlist Created";
	}
	
	@GetMapping(value = "/allwatchlists")
	public List<Watchlist> getAllWatchlists(){
		return watchlistService.getAllWatchlists();
	}
	
	@PutMapping(value = "/updatewatchlist/{id}")
	public String updateWatchlist(@RequestBody Watchlist wl, @PathVariable long id){
		//try to update
		try {
			if(watchlistService.idExists(id)) {
				watchlistService.updateWatchlist(id,wl);
			} else {
				return "id does not exists"; 
			}
			
		} catch (Exception e) {
			return "Unable to update bacause of error: " + e.getMessage(); 
		}
		return "Updated: " + id;
	}
	
	@DeleteMapping(value = "/deletewatchlist/{id}")
	public String deleteWatchlist(@PathVariable long id){
		//try to update
		try {
			watchlistService.deleteWatchlist(id);
		} catch (Exception e) {
			return "Unable to update bacause of error: " + e.getMessage(); 
		}
		return "Deleted: " + id;
	}
}
