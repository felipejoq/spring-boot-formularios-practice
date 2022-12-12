package com.uncodigo.springboot.form.app.models.domain;

import java.util.Date;

// import org.springframework.format.annotation.DateTimeFormat;

import com.uncodigo.springboot.form.app.validations.IdentificadorRegex;
import com.uncodigo.springboot.form.app.validations.Requerido;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.Past;
// import jakarta.validation.constraints.PastOrPresent;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

public class Usuario {

	// @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")
	@IdentificadorRegex
	private String identificador;

	@NotBlank
	@Size(min = 3, max = 8, message = "Debe ingresar un username entre 3 y 8 carácteres.")
	private String username;

	// @NotEmpty
	private String nombre;

	@NotEmpty
	private String password;

	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;

	// @NotEmpty
	@Requerido
	private String apellido;

	@NotNull // Sirve para objetos, para nativos usamos NotEmpty
	@Min(1)
	@Max(99)
	private Integer edad;

	@NotNull
	// @Past
	 @Future
	// @PastOrPresent
	// @FutureOrPresent
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

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

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String id) {
		this.identificador = id;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
