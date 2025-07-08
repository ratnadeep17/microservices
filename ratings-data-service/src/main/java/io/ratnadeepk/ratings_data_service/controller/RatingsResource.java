package io.ratnadeepk.ratings_data_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ratnadeepk.ratings_data_service.model.Rating;
import io.ratnadeepk.ratings_data_service.model.UserRatings;

@RestController
@RequestMapping("/ratingsdata/api")
public class RatingsResource {


	@GetMapping("/test")
	public String test() {
		return "Hello from Ratings..............";
	}
	
	@GetMapping("/movies/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 5);
	}
	
	@GetMapping("/user/{userId}")
	public UserRatings getUserRatings(@PathVariable("userId") String userId) {

		UserRatings userRatings = new UserRatings();
		userRatings.initData(userId);
		return userRatings;
	}
}
