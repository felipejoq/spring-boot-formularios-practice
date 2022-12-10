package com.uncodigo.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.uncodigo.springboot.form.app.models.domain.Usuario;

import jakarta.validation.Valid;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {

		model.addAttribute("titulo", "Formulario de prueba para usuarios:");

		return "form";

	}

	@PostMapping("/form")
	public String procesarForm(@Valid Usuario usuario, BindingResult result, Model model
	/*
	 * Primero va el objeto a recibir y valida, Luego BindingResult para recibir los
	 * resultados de la validación. BindingResult
	 * 
	 * @RequestParam("username") String username, -> Se le puede indicar
	 * explícitamente el nombre del parámetro.
	 * 
	 * @RequestParam String password
	 * 
	 * @RequestParam String email
	 */) {

		model.addAttribute("titulo", "Resultado del from:");

		if (result.hasErrors()) {
			/*
			 * Usando map para crear una lista de clave valor y así enviar los mensajes de
			 * error a la vista.
			 */
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(), "El campo ".concat(error.getField()).concat(error.getDefaultMessage()));
			});
			
			model.addAttribute("error", errores);

			return "form";
		}

		model.addAttribute("usuario", usuario);

		return "resultado";
	}

}
