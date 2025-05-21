package com.mx.ApiRestCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestCinepolis.model.Peliculas;
import com.mx.ApiRestCinepolis.service.PeliculasServImp;
@RestController
@RequestMapping(path = "webServicePeliculas")
@CrossOrigin
public class webServicePeliculas {

	@Autowired
	PeliculasServImp peliculasServImp;
	
	//http://localhost:9000/webServicePeliculas/mostrar
		@GetMapping(path = "mostrar")
		public List<Peliculas> mostrar(){
			return peliculasServImp.listar();
			
		}
		
		//Metodo para guardar peliculas
		//http://localhost:9000/webServicePeliculas/guardar
		//@RequestBody---te convierte el Json a objeto
			@PostMapping(path = "guardar")
			public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula) {
			
				try {
					String response = peliculasServImp.guardar(pelicula);
					if(response.equals("clavePeliculaYaExiste"))
						return new ResponseEntity<>("Esa clave de pelicula ya existe", HttpStatus.OK);
					else if(response.equals("nombreYaExiste"))
						return new ResponseEntity<>("Ese nombre de Pelicula ya existe", HttpStatus.OK);
					else
						return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
				} catch (Exception e) {
					return new ResponseEntity<>("Error al guardar", HttpStatus.OK);
				}
				
				
			}
			
			//Metodod para buscar
			//http://localhost:9000/webServicePeliculas/buscarXid
			@PostMapping(path = "buscarXid")
			public Peliculas buscarXid(@RequestBody Peliculas pelicula) {
				//TODO: process POST request
				
				return peliculasServImp.buscarXid(pelicula.getIdPelicula());
			}
			
			//Metodo para editar
			//http://localhost:9000/webServicePeliculas/editar
			@PutMapping(path = "editar")
			public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
				boolean response = peliculasServImp.editar(pelicula);
				
				if (response == true)
					return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
				else
					return new ResponseEntity<>("No se edito ese registro no existe", HttpStatus.OK);
			}
			
			//Metodo para eliminar
			//http://localhost:9000/webServicePeliculas/eliminar
			@DeleteMapping(path = "eliminar")
			public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula){
				boolean Response = peliculasServImp.eliminar(pelicula.getIdPelicula());
				
				if(Response == true)
					return new ResponseEntity<>("Eliminado Correctamente", HttpStatus.OK);
				else
					return new ResponseEntity<>("No se elimino, ese registro no existe", HttpStatus.OK);
			}
	
}
