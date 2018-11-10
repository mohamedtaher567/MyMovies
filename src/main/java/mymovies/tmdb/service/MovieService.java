package mymovies.tmdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.model.Movie;

@Service
public class MovieService {
	@Autowired
	APIConnector apiConnector;
	
	public List<Movie> getMoviesFromIds(Set<Integer> ids){
		List<Movie> movies = new ArrayList<Movie>();
		ids.forEach(id ->{
			try {
				movies.add(apiConnector.getMovie(id));
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		});
		return movies;
	}
}
