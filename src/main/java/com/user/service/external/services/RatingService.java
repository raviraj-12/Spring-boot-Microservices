package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.service.entity.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	
	//POST RATING
	@PostMapping("/ratings/create")
	Rating createRating(Rating values) ;
		
	//UPDATE RATING
   @PutMapping("/ratings/{ratingId}")
   Rating updateRating(@PathVariable String ratingId,Rating rating);
	
   
   //DELETE RATING
   @DeleteMapping("/ratings/{ratingId}")
   public void deleteRating(@PathVariable String ratingId);
}
