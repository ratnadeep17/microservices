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
import io.ratnadeepk.movie_catalog_service.model.UserRatings;

@RestController
@RequestMapping("/catalog/api")
public class CatalogResource {

	@GetMapping("/test")
	public String test() {
		return "Hello from movie catalog service";
	}

	@GetMapping("/user/{userId}")
	public List<CatalogItem> getCatalogItem(@PathVariable("userId") String userId) {
		RestTemplate restTemplate = new RestTemplate();
		// get all ratings
		UserRatings ratings = restTemplate.getForObject("http://localhost:8082/ratingsdata/api/user/" + userId,
				UserRatings.class);
		// get all rated movie ID's
		return ratings.getRatings().stream().map(rating -> {
			// For each movieId call movie info service and get details
			Movie movie = restTemplate.getForObject("http://localhost:8083/movies/api/" + rating.getMovieId(),
					Movie.class);
			// Put them all together
			return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
		}).collect(Collectors.toList());

	}

}
