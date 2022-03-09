package com.murilo.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.murilo.dsmovie.dto.MovieDTO;
import com.murilo.dsmovie.entities.Movie;
import com.murilo.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable){
		Page<Movie> movies = movieRepository.findAll(pageable);
		Page<MovieDTO> moviesDto = movies.map(x -> new MovieDTO(x));
		return moviesDto;
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(long id){
		Movie movie = movieRepository.findById(id).get();
		MovieDTO movieDto = new MovieDTO(movie); 
		return movieDto;
	}
	
}
