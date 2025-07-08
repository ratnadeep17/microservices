package io.ratnadeepk.movie_info_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ratnadeepk.movie_info_service.model.Movie;

@RestController
@RequestMapping("/movies/api/")
public class MovieResource {

	@GetMapping("/test")
	public String test() {
		return "Hello from movie info...............";
	}
	
	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "Special OPS", "Description");
	}
}
