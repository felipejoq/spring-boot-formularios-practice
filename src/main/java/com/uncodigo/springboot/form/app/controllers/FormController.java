package com.uncodigo.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

// import java.util.HashMap;
// import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.uncodigo.springboot.form.app.models.domain.Usuario;
import com.uncodigo.springboot.form.app.validations.UsuarioValidate;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidate validador;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();
		usuario.setNombre("Felipe");
		usuario.setApellido("Alphine");
		usuario.setIdentificador("11.111.111-1");

		model.addAttribute("titulo", "Formulario de prueba para usuarios:");
		model.addAttribute("usuario", usuario);

		return "form";

	}

	@PostMapping("/form")
	public String procesarForm(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

		/*
		 * @ModelAttribute("user") -> Para definir un nombre de variable personalizado y
		 * no el nombre de la clase que crea el objeto.
		 * 
		 * Primero va el objeto a recibir y valida, Luego BindingResult para recibir los
		 * resultados de la validación. BindingResult
		 * 
		 * @RequestParam("username") String username, -> Se le puede indicar
		 * explícitamente el nombre del parámetro.
		 * 
		 * @RequestParam String password
		 * 
		 * @RequestParam String email
		 * 
		 * Si en el formulario hay errores luego de la validación se inyecta
		 * automáticamente el objeto en la vista.
		 */

		validador.validate(usuario, result);

		model.addAttribute("titulo", "Resultado del from:");

		if (result.hasErrors()) {

			/*
			 * Usando map para crear una lista de clave valor y así enviar los mensajes de
			 * error a la vista.
			 */
			/*
			 * Para pasar de forma más "básica" la lista de errores a la vista.
			 * 
			 * Map<String, String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(error -> { errores.put(error.getField(),
			 * "El campo ".concat(error.getField()).concat(" ").concat(error.
			 * getDefaultMessage())); }); model.addAttribute("error", errores);
			 */

			return "form";
		}

		/*
		 * Podría incluir aquí la validación. Pero queda más ordenada con el InitBinder
		 * que detrás de escena hace la validación luego de crear el objeto desde el
		 * formulario.
		 * 
		 * model.addAttribute("usuario", usuario);
		 */

		status.setComplete();

		return "resultado";
	}

}
