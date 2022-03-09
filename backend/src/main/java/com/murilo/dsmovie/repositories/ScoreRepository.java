package com.murilo.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.murilo.dsmovie.entities.Score;
import com.murilo.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK>{
	
}
