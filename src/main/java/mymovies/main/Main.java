package mymovies.main;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import mymovies.model.Movie;

public class Main {
	private static final String DISCOVER_MOVIES_URL = "https://api.themoviedb.org/3/discover/movie";
	final static String API_KEY = "87c86a9929ed0835060be1d75e295195";
	
	public static void main(String[] args) {
		
	}
}
