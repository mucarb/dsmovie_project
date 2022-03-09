package com.murilo.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.murilo.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
