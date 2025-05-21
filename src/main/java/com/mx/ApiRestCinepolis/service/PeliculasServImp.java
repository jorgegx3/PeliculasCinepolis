package com.mx.ApiRestCinepolis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestCinepolis.dao.PeliculasDao;
import com.mx.ApiRestCinepolis.model.Peliculas;

@Service
public class PeliculasServImp {

	@Autowired
	PeliculasDao peliculaDao;
	
	//Esto quiere decir que no vas a realizar cambios en la base de datos
		@Transactional(readOnly = true)
		public List<Peliculas> listar(){
			List<Peliculas> registrosBD = peliculaDao.findAll();
			
			return registrosBD;
		}
		
		
		//Guardar: validar que el clavePelicula y nombrePelicula no se repita, guardar
		@Transactional
		public String guardar(Peliculas pelicula) {
			//Desarrollo de la logica
			//1---Obtener los registros de la tabla
			//2---Ciclo para recorrer registors
			//3---Condicional
			//4---Banderas
			String respuesta = "guardado";
			boolean bandera=false;
			for (Peliculas p:peliculaDao.findAll()) {
				
				//Los tipos de datos primitivos que no estan parseados se comparan con el ==
				//los tipos de datos primitivos pero que estan parseados se comparan con el .equals
				
				//Los que no son tipos de datos primitivos con el .equals
				if(p.getClavePelicula().equals(pelicula.getClavePelicula())) {
					respuesta  = "clavePeliculaYaExiste";
					bandera=true;
					break;
					}else if(p.getNombrePelicula().equals(pelicula.getNombrePelicula())) {
						respuesta  = "nombreYaExiste";
						bandera=true;
						break;
					}
			}
			if(!bandera)
				peliculaDao.save(pelicula);
			
			return respuesta;
		}
		
		@Transactional(readOnly = true)
		public Peliculas buscarXid(Long id) {
			Peliculas peliEncon = peliculaDao.findById(id).orElse(null);
			return peliEncon;
		}
		
		
		@Transactional
		public boolean editar(Peliculas pelicula) {
			Peliculas peliEncon = peliculaDao.findById(pelicula.getIdPelicula()).orElse(null);
			
			if(peliEncon != null) {
				peliculaDao.save(pelicula);
				return true;
			}else
				return false;
		}
		
		@Transactional
		public boolean eliminar(Long id) {
			Peliculas peliEncon = peliculaDao.findById(id).orElse(null);
			
			if(peliEncon != null) {
				peliculaDao.deleteById(id);
				return true;
			}else
				return false;
			
		
	}
		
		
}
