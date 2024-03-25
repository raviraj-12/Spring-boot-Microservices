package com.user.service.serviceimp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repository.UserRepository;
import com.user.service.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
   private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
   
   @Autowired
   private HotelService hotelService;
	
   
   
   
	@Override
	public User saveUser(User user) {
		
		//generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		
		user.setUserId(randomUserId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		 
		//get user from database with the help of user repository
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not foundeeeeeeee   "+  userId));
		 
		
		// fetch rating of the above user from RATING SERVICE
		//http://localhost:8083/ratings/users/089c3177-13ef-4a8f-844f-347830a33fef
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		logger.info("{}",ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		
		//getting rating one by one
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			//api call to HOTEL SERVICE to get the hotel using RestTemplate		
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getById/"+rating.getHotelId(), Hotel.class);	
		 //	Hotel hotel = forEntity.getBody();
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			//set the hotel to rating			
			rating.setHotel(hotel);
			
			// return the rating			
			return rating;
									
		}).collect(Collectors.toList());

				
		user.setRatings(ratingList);
				
		 return user;
	}

	
	
	@Override
	public void delete(String userId) {
		// TODO Auto-generated method stub
		
	}

	
	}


