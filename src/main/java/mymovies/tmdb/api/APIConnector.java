package mymovies.tmdb.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.model.Movie;

@Component
public class APIConnector {
	final static String API_KEY = "87c86a9929ed0835060be1d75e295195";
	private static final String DISCOVER_MOVIES_URL = "https://api.themoviedb.org/3/discover/movie";
	
	public int getMoviesPageCount() throws UnirestException {
		String url = DISCOVER_MOVIES_URL;
		HttpResponse<JsonNode> response = Unirest.get(url)
		  .queryString("api_key", API_KEY)
		  .asJson();
		return Integer.parseInt(response.getBody().getObject().getString("total_pages"));
	}
	
	public Movie[] listMovies(int page) throws UnirestException {
		String url = DISCOVER_MOVIES_URL;
		HttpResponse<JsonNode> response = Unirest.get(url)
		  .queryString("api_key", API_KEY)
		  .queryString("page", page)
		  .asJson();
		JSONArray moviesJsonArray =  (JSONArray) response.getBody().getObject().get("results");
		Movie[] movies = new Movie[moviesJsonArray.length()];
		for(int i=0; i<moviesJsonArray.length(); i++) {
			movies[i] = createMovieObjectFromJson(((JSONObject) moviesJsonArray.get(i)));
		}
		return movies;
	}
	
	private Movie createMovieObjectFromJson(JSONObject movieJson) {
		Movie movie = new Movie();
		movie.setName(objectToString(movieJson.get("title")));
		movie.setLanguage(objectToString(movieJson.get("original_language")));
		movie.setYear(objectToString(movieJson.get("release_date")));
		movie.setLogoPath(objectToString(movieJson.get("backdrop_path")));
		return movie;
	}
	
	private String objectToString(Object object) {
		if(object == null) {
			return null;
		}
		return object.toString();
	}
}
