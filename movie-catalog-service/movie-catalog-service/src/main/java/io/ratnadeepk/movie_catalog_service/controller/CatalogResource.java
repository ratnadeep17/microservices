package io.ratnadeepk.movie_catalog_service.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.ratnadeepk.movie_catalog_service.model.Rating;

@RestController
@RequestMapping("/catalog/api")
public class CatalogResource {

	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/test")
	public String test() {
		return "Hello from movie catalog..............................................";
	}

	List<Rating> ratings = Arrays.asList(new Rating("12345", 4), new Rating("45678", 5));

}
