package com.pruebatecnica.crud.CRUDCLIENTES.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id_cliente;

private String nombre_cliente;

private String apellido_cliente;

private String correo_electronico;

private int edad;

private String genero;

private String direccion;

private Date fecha_registro;

private LocalDateTime ultima_modificacion;




public Cliente() {
	
}




public Cliente(String nombre_cliente, String apellido_cliente, String correo_electronico, int edad,
		String genero, String direccion, Date fecha_registro, LocalDateTime ultima_modificacion) {
	this.nombre_cliente = nombre_cliente;
	this.apellido_cliente = apellido_cliente;
	this.correo_electronico = correo_electronico;
	this.edad = edad;
	this.genero = genero;
	this.direccion = direccion;
	this.fecha_registro = fecha_registro;
	this.ultima_modificacion = ultima_modificacion;
}



public String getNombre_cliente() {
	return nombre_cliente;
}

public void setNombre_cliente(String nombre_cliente) {
	this.nombre_cliente = nombre_cliente;
}

public String getApellido_cliente() {
	return apellido_cliente;
}

public int getId_cliente() {
	return id_cliente;
}




public void setId_cliente(int id_cliente) {
	this.id_cliente = id_cliente;
}




public void setApellido_cliente(String apellido_cliente) {
	this.apellido_cliente = apellido_cliente;
}

public String getCorreo_electronico() {
	return correo_electronico;
}

public void setCorreo_electronico(String correo_electronico) {
	this.correo_electronico = correo_electronico;
}

public int getEdad() {
	return edad;
}

public void setEdad(int edad) {
	this.edad = edad;
}

public String getGenero() {
	return genero;
}

public void setGenero(String genero) {
	this.genero = genero;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public Date getFecha_registro() {
	return fecha_registro;
}

public void setFecha_registro(Date fecha_registro) {
	this.fecha_registro = fecha_registro;
}

public LocalDateTime getUltima_modificacion() {
	return ultima_modificacion;
}

public void setUltima_modificacion(LocalDateTime ultima_modificacion) {
	this.ultima_modificacion = ultima_modificacion;
}




	
	
	
}
