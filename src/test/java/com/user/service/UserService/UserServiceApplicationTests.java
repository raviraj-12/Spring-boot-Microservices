package com.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.entity.Rating;
import com.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Autowired
//	private RatingService ratingService;
//
//	@Test
//	void createRating() {
//
//		Rating rating = Rating.builder().rating(7).userId("").hotelId("").feedback("This is craeted using feign client")
//				.build();
//
//		Rating savedRating = ratingService.createRating(rating);
//
//		System.out.println("new Rating craeted     "+savedRating);
//	}
}
