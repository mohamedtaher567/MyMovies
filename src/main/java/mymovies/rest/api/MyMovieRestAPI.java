package mymovies.rest.api;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.file.utils.MoviesFileUtils;
import mymovies.tmdb.service.APIConnector;
import mymovies.tmdb.service.MovieService;

@Controller
@RequestMapping("movies")
public class MyMovieRestAPI {
	@Autowired
	APIConnector apiConnector;	
	
	@Autowired
	MoviesFileUtils fileUtils;
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping({"{page_number}", "/"})
	public String index(Map<String, Object> model, @PathVariable("page_number")  Optional<Integer>  pageNumberParam) throws UnirestException {
		Integer pageNumber = pageNumberParam.isPresent() ? pageNumberParam.get() : 1;
		model.put("movies", apiConnector.listMovies(pageNumber));
		model.put("pageNumber", pageNumber);
		return "movies";
	}

	@RequestMapping({"watched"})
	public String getWatched(Map<String, Object> model) throws IOException {
		model.put("movies", movieService.getMoviesFromIds(fileUtils.readMovieIds("watched_movies.lib")));
		model.put("wantToWatch", false);
		return "movies";
	}
	
	@RequestMapping({"want_to_watch"})
	public String getWantToWatch(Map<String, Object> model) throws IOException {
		model.put("movies", movieService.getMoviesFromIds(fileUtils.readMovieIds("want_to_watch_movies.lib")));
		model.put("wantToWatch", true);
		return "movies";
	}
	
	@RequestMapping(value = "add_to_watch", method = RequestMethod.POST)
	@ResponseBody
	public Integer addToWatched(@RequestParam Integer id) throws IOException {
		fileUtils.writeMovieId("watched_movies.lib", id);
		return id;
	}
	
	@RequestMapping(value = "remove_from_watch", method = RequestMethod.POST)
	@ResponseBody
	public Integer reomveFromWatched(@RequestParam Integer id) throws IOException {
		fileUtils.removeLineFromFile("watched_movies.lib", id);
		return id;
	}
	
	@RequestMapping(value = "add_to_want_to_watch", method = RequestMethod.POST)
	@ResponseBody
	public Integer addToWantWatched(@RequestParam Integer id) throws IOException {
		fileUtils.writeMovieId("want_to_watch_movies.lib", id);
		return id;
	}
	
	@RequestMapping(value = "remove_from_want_to_watch", method = RequestMethod.POST)
	@ResponseBody
	public Integer reomveFromWantToWatch(@RequestParam Integer id) throws IOException {
		fileUtils.removeLineFromFile("want_to_watch_movies.lib", id);
		return id;
	}

}