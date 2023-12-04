package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centroinformacion.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

	
	@Query(value="SELECT * FROM autor WHERE nombres LIKE ?1%", nativeQuery = true)
	public List<Autor> obtenerAutorPorNombre(String nombre);
	
	
	@Query(value="select * from autor where nombres like ?1 and (?2='' or apellidos =?2) and (?3='' or telefono =?3) and (?4='' or estado =?4)", nativeQuery = true)
	public List<Autor> listaPorFiltros(String nombre,String apellido,String telefono,int estado);
	
	
}
