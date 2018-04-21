package com.stocks.watchlist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.watchlist.config.CustomUserDetails;
import com.stocks.watchlist.models.User;
import com.stocks.watchlist.models.Watchlist;
import com.stocks.watchlist.services.UserService;
import com.stocks.watchlist.services.WatchlistService;

@RestController
public class WatchlistController {
	@Autowired
	private WatchlistService watchlistService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value = "/allwatchlists")
	public List<Watchlist> getAllWatchlists(){
		return watchlistService.getAllWatchlists();
	}
	
	//Read only, not protected
	//Can be protected by testing for userDatail.getUsername();
	@GetMapping(value = "/allwatchlists/{username}")
	public List<Watchlist> getAllWatchlistsbyUser(@PathVariable String username){
		//userService.getUser(username) looks for the user;
		//then findByUser does select * from joinedtable where userid = userid;
		if( userService.getUser(username) == null) {
			return new ArrayList<Watchlist>();
		}
		return watchlistService.findByUser(userService.getUser(username));
	}
	
	//Create a watchlist
	@PostMapping(value = "/watchlist")
	public String createWatchlist(@RequestBody Watchlist wl){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(wl.getTickers() == null) {
			wl.setTickers(new ArrayList<String>());
		}
		wl.setOwner(userService.getUser(userDetails.getUsername()));
		watchlistService.insert(wl);
		return "Watchlist Created";
	}
	
	
	//update watchlist
	@PutMapping(value = "/watchlist/{id}")
	public String updateWatchlist(@RequestBody Watchlist wl, @PathVariable long id){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(watchlistService.idExists(id)) {
			User ownerOfWatchlist = watchlistService.owner(id).getOwner();
			User currentUser = userService.getUser(userDetails.getUsername());
			if(ownerOfWatchlist.equals(currentUser)) {
				if(id == wl.getId()) {
					watchlistService.updateWatchlist(id,wl);
					return "Updated: " + id;
				} else {
					return "id param does not match id of watchlist";
				}
			} else {
				return "you are not the owner";
			}
		} else {
			return "id does not exists";
		}		
	}
	
	//delete watchlist
	@DeleteMapping(value = "/watchlist/{id}")
	public String deleteWatchlist(@PathVariable long id){
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(watchlistService.idExists(id)) {
			User ownerOfWatchlist = watchlistService.owner(id).getOwner();
			User currentUser = userService.getUser(userDetails.getUsername());
			if(ownerOfWatchlist.equals(currentUser)) {
				watchlistService.deleteWatchlist(id);
				return "Deleted: " + id;
			} else {
				return "you are not the owner";
			}
		} else {
			return "id does not exists";
		}
	}
}