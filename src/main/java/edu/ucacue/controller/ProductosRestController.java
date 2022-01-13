package edu.ucacue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.ucacue.infraestructura.repository.PersonaRepositorio;
import edu.ucacue.infraestructura.repository.ProductosRepositorio;
import edu.ucacue.modelo.Persona;
import edu.ucacue.modelo.Producto;

public class ProductosRestController {

	//@CrossOrigin(origins = { "http://localhost:4200" })
	@RestController
	@RequestMapping("/api")
	public class PersonaRestController {

		@Autowired
		private ProductosRepositorio productosRepositorio;

		@GetMapping("/Productos")
		public List<Producto> index() {
			return productosRepositorio.findAll();
		}

		@GetMapping("/Productos/{id}")
		public Producto getById(@PathVariable int id) {

			Producto producto = productosRepositorio.findById(id).get();
			return producto;
		}

		@GetMapping("/Producto/nombre")
		public List<Producto> getByNombre(@RequestParam(name = "nombre") String nombre) {

			List<Producto> producto = productosRepositorio.findAllByNombre(nombre);
			return producto;
		}

		@PostMapping("/Producto")
		public ResponseEntity<?> saveCliente(@RequestBody Producto producto, BindingResult result) {
			Producto productoGrabar;
			Map<String, Object> response = new HashMap<>();

			if (result.hasErrors()) {
				List<String> errores = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("Los errores son", errores);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			try {
				productoGrabar = productosRepositorio.save(producto);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el inserción en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "El Producto se ha insertado con éxito en la BD");
			response.put("Producto", productoGrabar);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

		@PutMapping("/Producto/{id}")
		public ResponseEntity<?> modificarCliente(@RequestBody Producto producto, BindingResult result,
				@PathVariable int id) {
			
			
			Map<String, Object> response = new HashMap<>();
			Producto pro;
			try {
			pro=productosRepositorio.getById(id);
			}catch(DataAccessException e) {
		
					response.put("mensaje", "Error: no se pudo editar, el Producto ID: "
							.concat(id + " no existe en la base de datos!"));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			Producto productoModificado = new Producto();
			
			
			if (result.hasErrors()) {
				List<String> errores = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("Los errores son", errores);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			

			
			try {
				pro.setCantidad(producto.getCantidad());
				pro.setDistribuidor(producto.getDistribuidor());
				pro.setNombre(producto.getNombre());
				
				
				
				productoModificado= productosRepositorio.save(producto); 
				
			}catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar el cliente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (Exception e) {
				response.put("mensaje", "Error al actualizar el cliente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			response.put("mensaje", "El Cliente se ha modificado con éxito en la BD");
			response.put("Producto", productoModificado);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		@DeleteMapping("/Producto/{id}")
		public ResponseEntity<?> eliminarCliente(
				@PathVariable int id) {
			
			
			Map<String, Object> response = new HashMap<>();
			try {
				productosRepositorio.deleteById(id);
			}catch(DataAccessException e) {
		
					response.put("mensaje", "Error: no se pudo eliminar, el Producto ID: "
							.concat(id + " no existe en la base de datos!"));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
				
			
			response.put("mensaje", "El Producto se ha eliminado con éxito en la BD");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
	}

}
