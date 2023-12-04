package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.service.AutorServiceImpl;
import com.centroinformacion.util.AppSettings;

import jakarta.ws.rs.NotFoundException;

@RestController
@RequestMapping("/rest/autor")
public class AutorController {

	//   http://localhost:8080/rest/autor/listaAutor
	//   http://localhost:8080/rest/autor/listaAutorPorNombreApellido/cesar
	//   http://localhost:8080/rest/autor/registraAutor
	/*
	 {
		"nombres": "Rodolfo",
		"apellidos": "espinoza",
		"fechaNacimiento": "2020-10-11",
		"telefono": "912345678",
		"fechaRegistro": "2022-04-04 04:59:07",
		"fechaActualizacion": "2022-04-04 04:59:07",
		"estado": 1,
		"pais": {
			"idPais": 4,
			"iso": "DE",
			"nombre": "Alemania"
		},
		"grado": {
			"idDataCatalogo": 11,
			"descripcion": "Técnico",
			"catalogo": {
				"idCatalogo": 4,
				"descripcion": "Grado de Autor"
			}
		},
		"usuarioRegistro": {
			"idUsuario": 2,
			"nombres": "Ana Elba",
			"apellidos": "Flores Enero",
			"dni": "87485965",
			"login": "ana",
			"password": "$2a$10$LKANug7Towq30DMm/GsCNOqJ6ybfWjqC/8FKZX78Fp3r4ZPvVfHSO",
			"correo": "ana@gmail.com",
			"fechaRegistro": "2022-04-04 03:59:07",
			"fechaNacimiento": "1997-10-10",
			"nombreCompleto": "Ana Elba Flores Enero"
		},
		"usuarioActualiza": {
			"idUsuario": 2,
			"nombres": "Ana Elba",
			"apellidos": "Flores Enero",
			"dni": "87485965",
			"login": "ana",
			"password": "$2a$10$LKANug7Towq30DMm/GsCNOqJ6ybfWjqC/8FKZX78Fp3r4ZPvVfHSO",
			"correo": "ana@gmail.com",
			"fechaRegistro": "2022-04-04 03:59:07",
			"fechaNacimiento": "1997-10-10",
			"nombreCompleto": "Ana Elba Flores Enero"
		}
	}
	 */
	//   http://localhost:8080/rest/autor/actualizaAutor
	//   http://localhost:8080/rest/autor/eliminaAutor/x
	
	
	//CONSULTA PARA EL PROYECTO
	///--->  http://localhost:8080/rest/autor/ConsultaAutorPorParametros?nombre=Cesar&apellido=Vallejo&telefono=912345678&estado=1

    @Autowired
	private AutorServiceImpl service;
	
	@GetMapping("/listaAutor")
	public ResponseEntity<List<Autor>> lista(){
		
		List<Autor> lista=service.findAll();
		
		if(lista.size()<=0) {
			
			throw new NotFoundException();
			
		}else {
			
			
			return new ResponseEntity<>(lista,HttpStatus.OK);
			
		}
		
		
	}
	
	
	@GetMapping("/listaAutorPorNombreApellido/{nombre}")
	public ResponseEntity<List<Autor>> listaPorNombre(@PathVariable("nombre")String nombree){
		
		List<Autor> lista=service.obtenerPorNombre(nombree);
		List<Autor> listaTodos=service.obtenerPorNombre("");
		
        if(lista.size()<=0) {
			
        	return new ResponseEntity<>(listaTodos,HttpStatus.OK);
			
		}else {
			
			
			return new ResponseEntity<>(lista,HttpStatus.OK);
			
		}
		
		
	}
	
	
	@PostMapping("/registraAutor")
	public ResponseEntity<Map<String, Object>> registrar(@RequestBody Autor obj){
		
		Map<String, Object> map= new HashMap<>();
		
		
		try {
			
			obj.setFechaRegistro(new Date());
			obj.setFechaActualizacion(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			Autor	response=service.SaveAutor(obj);
			
			if (response==null) {
				map.put("respuesta", "Error");
			}else {
				map.put("respuesta", response);
			//	return new ResponseEntity<>(response,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new ResponseEntity<>(map,HttpStatus.OK);
		
		
	}
	
	
	@PutMapping("/actualizaAutor")
	public ResponseEntity<Autor> actualizar(@RequestBody Autor obj){
		
		Autor response=service.findById(obj.getIdAutor());
		
		if(response==null) {
			throw new NotFoundException();
		}else {
			
			obj.setFechaActualizacion(new Date());
			service.Update(obj);
			 return new ResponseEntity<>(response,HttpStatus.OK);
		}
		
		
	}
	
	
	@DeleteMapping("/eliminaAutor/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id")int cod){
		
		Autor response=service.findById(cod);
		
		if(response==null) {
			throw new NotFoundException();
		}else {
			
			service.DeleteById(cod);
			
			 return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
	}
	
	///////////////////////////////////////////////////----> proyecto
	// http://localhost:8080/rest/autor/ConsultaAutorPorParametros?nombre=Cesar&apellido=Vallejo&telefono=912345678&estado=1
	
	@GetMapping("/ConsultaAutorPorParametros")
	public ResponseEntity<List<Autor>> listas(@RequestParam(name = "nombre", required = false , defaultValue = "Cesar")String nombre,
		                                    	@RequestParam(name = "apellido", required = false , defaultValue = "Vallejo")String apellido,
		                                    	@RequestParam(name = "telefono", required = false , defaultValue = "912345678")String telefono,
		                                    	@RequestParam(name = "estado", required = false , defaultValue = "1")int estado){
		System.out.println(nombre);
		System.out.println(apellido);
		System.out.println(telefono);
		System.out.println(estado);
		List<Autor> lista=service.listaPorFiltros("%"+nombre+"%", apellido, telefono, estado);
		
		 return new ResponseEntity<>(lista,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
}
