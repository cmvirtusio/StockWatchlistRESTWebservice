package com.stocks.watchlist;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stocks.watchlist.config.CustomUserDetails;
import com.stocks.watchlist.models.Role;
import com.stocks.watchlist.models.User;
import com.stocks.watchlist.repositories.UserRepository;
import com.stocks.watchlist.services.UserService;

@SpringBootApplication
public class WatchlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlistApplication.class, args);
	}
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
     */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
		//Setup a default user if db is empty
//		if (repository.count()==0) {
//			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
//			service.save(new User("user1", "user1", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
//			service.save(new User("user2", "user2", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
//		}
			
		
		//Helper Function
//		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);

		//Anonymous class
//		builder.userDetailsService(new UserDetailsService() {
//
//			@Override
//			public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//				// TODO Auto-generated method stub
//				return new CustomUserDetails(repository.findByUsername(s));
//			}
//		}).passwordEncoder(passwordEncoder);
		
		//Java 8 lambda
		builder.userDetailsService((username)-> {
			return new CustomUserDetails(repository.findByUsername(username));
		}).passwordEncoder(passwordEncoder);
	}

	/**
	 * We return an instance of our CustomUserDetails.
	 * @param repository
	 * @return
     */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
}
