package edu.ucacue.infraestructura.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.modelo.DetalleFactura;

public interface DetalleFacturaRepositorio extends JpaRepository<DetalleFactura, Integer> {

}

