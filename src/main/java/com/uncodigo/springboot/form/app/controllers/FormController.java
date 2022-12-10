package com.uncodigo.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		
		model.addAttribute("titulo", "Formulario de prueba para usuarios:");

		return "form";

	}

	@PostMapping("/form")
	public String procesarForm(Model model,
			@RequestParam("username") String username, // Se le puede indicar explícitamente el nombre del parámetro.
			@RequestParam String password,
			@RequestParam String email) {
		
		model.addAttribute("titulo", "Resultado del from:");
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("email", email);

		return "resultado";
	}

}
