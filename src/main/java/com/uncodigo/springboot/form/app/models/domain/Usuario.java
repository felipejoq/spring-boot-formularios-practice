package com.uncodigo.springboot.form.app.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Usuario {

	private String id;

	@NotEmpty(message = "El username no puede ser vació")
	@Size(min = 3, max = 8, message = "Debe ingresar un username entre 3 y 8 carácteres.")
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
