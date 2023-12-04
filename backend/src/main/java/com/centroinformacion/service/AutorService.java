package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Autor;

public interface AutorService {

	
	 
	public abstract Autor SaveAutor(Autor obj);
	public abstract Autor findById(int cod);
	public abstract Autor Update(Autor obj);
	public abstract void DeleteById(int cod);
	public abstract List<Autor> findAll();	
	public abstract List<Autor> obtenerPorNombre(String nombre);
	 
	//Proyecto
	
	public abstract List<Autor> listaPorFiltros(String nombre,String apellido,String telefono,int estado);
	 
	 
	
}
