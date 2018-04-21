package com.stocks.watchlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stocks.watchlist.models.User;
import com.stocks.watchlist.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    
    //Encodes everything {bcrypt}
    //Important for Spring Security 5.0
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void save(User user){
    	//Spring 5    
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

	public User getUser(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
