package com.uncodigo.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uncodigo.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	public List<Pais> lista;
	
	public PaisServiceImpl () {
		this.lista = Arrays.asList(
				new Pais(1, "CL", "Chile"),
				new Pais(2, "ES", "España"),
				new Pais(3, "MX", "México"),
				new Pais(4, "AR", "Argentina"),
				new Pais(5, "BR", "Brasil")
				);
	}

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Pais obtenerPais(Integer id) {
		
		Pais resultado = null;
		
		for (Pais pais : this.lista) {
			if (id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		
		return resultado;
	}

}
