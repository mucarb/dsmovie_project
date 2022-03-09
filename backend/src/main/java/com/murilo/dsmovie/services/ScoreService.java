package com.murilo.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.murilo.dsmovie.dto.MovieDTO;
import com.murilo.dsmovie.dto.ScoreDTO;
import com.murilo.dsmovie.entities.Movie;
import com.murilo.dsmovie.entities.Score;
import com.murilo.dsmovie.entities.User;
import com.murilo.dsmovie.repositories.MovieRepository;
import com.murilo.dsmovie.repositories.ScoreRepository;
import com.murilo.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO scoreDto) {
		User user = userRepository.findByEmail(scoreDto.getEmail());
		
		if(user == null) {
			user = new User();
			
			user.setEmail(scoreDto.getEmail());			
			user = userRepository.saveAndFlush(user);
		}
		
		Movie movie = movieRepository.findById(scoreDto.getMovieId()).get();
		
		Score score = new Score();
		
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(scoreDto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		
		for(Score s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	
}
