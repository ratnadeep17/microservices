package io.ratnadeepk.movie_catalog_service.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.ratnadeepk.movie_catalog_service.model.CatalogItem;
import io.ratnadeepk.movie_catalog_service.model.Movie;
import io.ratnadeepk.movie_catalog_service.model.Rating;

@RestController
@RequestMapping("/catalog/api")
public class CatalogResource {


	@GetMapping("/test")
	public String test() {
		return "Hello from movie catalog..............................................";
	}

	@GetMapping("/user/{userId}")
	public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId) {
		RestTemplate restTemplate = new RestTemplate();
		
		// get all rated movie ID's
		List<Rating> ratings = Arrays.asList(new Rating("12345", 4), new Rating("45678", 5));

		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8083/movies/api/" + rating.getMovieId() , Movie.class);
			return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
					}).collect(Collectors.toList()
		);
		

		// For each movieId call movie info service and get details

		// Put them all together
	}

}
