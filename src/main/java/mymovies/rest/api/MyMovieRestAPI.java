package mymovies.rest.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.tmdb.api.APIConnector;

@Controller
@RequestMapping("movies")
public class MyMovieRestAPI {
	@Autowired
	APIConnector apiConnector;

	@RequestMapping({"{page_number}", "/"})
	public String index(Map<String, Object> model, @PathVariable("page_number")  Optional<Integer>  pageNumberParam) throws UnirestException {
		Integer pageNumber = pageNumberParam.isPresent() ? pageNumberParam.get() : 1;
		model.put("movies", apiConnector.listMovies(pageNumber));
		model.put("pageNumber", pageNumber);
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