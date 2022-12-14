package com.uncodigo.springboot.form.app.services;

import java.util.List;

import com.uncodigo.springboot.form.app.models.domain.Pais;

public interface PaisService {

	public List<Pais> listar();
	
	public Pais obtenerPais(Integer id);
	
}
