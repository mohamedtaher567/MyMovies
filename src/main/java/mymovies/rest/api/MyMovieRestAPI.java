package mymovies.rest.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.tmdb.api.APIConnector;

@Controller
public class MyMovieRestAPI {
	@Autowired
	APIConnector apiConnector;
	
    @RequestMapping("/movies")
    public String index(Map<String, Object> model) throws UnirestException {
    	model.put("data", apiConnector.listMovies(1));
        return "movies";
    }

}