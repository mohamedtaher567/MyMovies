package mymovies.file.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class MoviesFileUtils {

	public Set<Integer> readMovieIds(String filePath) throws IOException {
		Set<Integer> result = new HashSet<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				result.add(Integer.parseInt(line));
			}
		}
		return result;
	}

	public void writeMovieId(String filePath, Integer id) throws IOException {
		FileWriter fw = new FileWriter(filePath, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw);
	    out.println(id);
	    out.flush();
	    out.close();
	}
	
	public void removeLineFromFile(String filePath, Integer Id) throws IOException {
		File file = new File(filePath);
		File temp = new File("temp_"+filePath);

		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		String line;
		while((line = reader.readLine()) != null) {
		    if(!line.trim().equals(Id.toString())) { 
		    	writer.write(line + System.getProperty("line.separator"));
		    }
		}
		writer.close(); 
		reader.close(); 
		temp.renameTo(file);
	}
}
