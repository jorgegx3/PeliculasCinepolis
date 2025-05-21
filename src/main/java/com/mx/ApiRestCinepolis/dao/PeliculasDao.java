package com.mx.ApiRestCinepolis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ApiRestCinepolis.model.Peliculas;

public interface PeliculasDao extends JpaRepository<Peliculas, Long>{

	
	
}
