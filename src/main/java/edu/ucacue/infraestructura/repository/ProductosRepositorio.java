package edu.ucacue.infraestructura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ucacue.modelo.Producto;



public interface ProductosRepositorio extends JpaRepository<Producto, Integer>{
	
	@Query("select p from Persona p where p.nombre like %:nombre%")
	List<Producto> findAllByNombre(String nombre);

}
