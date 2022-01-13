package edu.ucacue.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "productos_table")
public class Producto {

	
	


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		@Column(name = "nombre_pro", columnDefinition = "text")
		private String nombre;
		
		@Column(length = 30)	
		private double precioporunidad;
		
		@Column(length = 30)
		private String cantidad;

		@Column(length = 30)
		private String distribuidor;

		public Producto(String nombre, double string, String cantidad, String distribuidor) {
			super();
			this.nombre = nombre;
			this.precioporunidad = string;
			this.cantidad = cantidad;
			this.distribuidor = distribuidor;
		}
		
		
		public Producto(int id, String nombre, double precioporunidad, String cantidad, String distribuidor) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.precioporunidad = precioporunidad;
			this.cantidad = cantidad;
			this.distribuidor = distribuidor;
		}


		public Producto() {
			super();
		}

		public Producto(String text, String text2, String text3, String text4) {
			// TODO Auto-generated constructor stub
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public double getPrecioporunidad() {
			return precioporunidad;
		}

		public void setPrecioporunidad(double d) {
			this.precioporunidad = d;
		}

		public String getCantidad() {
			return cantidad;
		}

		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}

		public String getDistribuidor() {
			return distribuidor;
		}

		public void setDistribuidor(String distribuidor) {
			this.distribuidor = distribuidor;
		}

		@Override
		public String toString() {
			return "Persona [id=" + id + ", nombre=" + nombre + ", precio por unidad=" + precioporunidad + ", cantidad="
					+ cantidad + ", distribuidor=" + distribuidor + "]";
		}

	}

