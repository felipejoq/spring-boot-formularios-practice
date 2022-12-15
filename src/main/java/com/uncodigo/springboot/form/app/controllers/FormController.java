package com.uncodigo.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.uncodigo.springboot.form.app.editors.NombreMayusculaPropertyEditor;
import com.uncodigo.springboot.form.app.editors.PaisPropertyEditor;
import com.uncodigo.springboot.form.app.editors.RolesPropertyEditor;
import com.uncodigo.springboot.form.app.models.domain.Pais;
import com.uncodigo.springboot.form.app.models.domain.Role;
import com.uncodigo.springboot.form.app.models.domain.Usuario;
import com.uncodigo.springboot.form.app.services.PaisService;
import com.uncodigo.springboot.form.app.services.RoleService;
import com.uncodigo.springboot.form.app.validations.UsuarioValidate;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidate validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesPropertyEditor roleEditor;

	@Autowired
	private RoleService roleService;;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaPropertyEditor());

		binder.registerCustomEditor(Pais.class, "pais", paisEditor);

		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Chile", "España", "México", "Argentina", "Brasil");
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();

		paises.put("ES", "España");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");

		return paises;
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<>();

		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		
		return this.roleService.listar();
	}
	
	@ModelAttribute("genero")
	public List<String> genero() {
		
		return Arrays.asList("Masculino", "Femenino", "No informa");
	}

	@GetMapping("/form")
	public String form(Model model) {

		Usuario usuario = new Usuario();
		usuario.setNombre("Felipe");
		usuario.setApellido("Alphine");
		usuario.setIdentificador("11.111.111-1");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Este valor es secreto...");
		usuario.setPais(new Pais(1, "CL", "Chile"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));

		model.addAttribute("titulo", "Formulario de prueba para usuarios:");
		model.addAttribute("usuario", usuario);

		return "form";

	}

	@PostMapping("/form")
	public String procesarForm(@Valid Usuario usuario, BindingResult result, Model model) {

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

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado del from:");
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



		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		
		if(usuario==null) {
			return "redirect:/form";
		}
		
		model.addAttribute("titulo", "Resultado del from:");
		
		status.setComplete();
		
		return "resultado";
	}

}
