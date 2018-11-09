package mymovies.rest.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.tmdb.api.APIConnector;

@Controller
public class MyMovieRestAPI {
	@Autowired
	APIConnector apiConnector;

	@RequestMapping("movies")
	public String index(Map<String, Object> model) throws UnirestException {
		model.put("movies", apiConnector.listMovies(1));
		return "movies";
	}

	@RequestMapping(value = "add_to_watch", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> addToWatch() {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", true);
		return map;
	}

}