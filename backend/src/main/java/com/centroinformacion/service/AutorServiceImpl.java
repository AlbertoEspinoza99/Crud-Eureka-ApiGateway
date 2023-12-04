package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	private AutorRepository repo;
	
	@Override
	public Autor SaveAutor(Autor obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Autor Update(Autor obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public void DeleteById(int cod) {
		repo.deleteById(cod);
		
	}

	@Override
	public List<Autor> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Autor findById(int cod) {
		// TODO Auto-generated method stub
		return repo.findById(cod).orElseThrow(null);
	}

	@Override
	public List<Autor> obtenerPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return repo.obtenerAutorPorNombre(nombre);
	}

	@Override
	public List<Autor> listaPorFiltros(String nombre, String apellido, String telefono, int estado) {
		// TODO Auto-generated method stub
		return repo.listaPorFiltros(nombre, apellido, telefono, estado);
	}

}
