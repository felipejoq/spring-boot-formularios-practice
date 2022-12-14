package com.uncodigo.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uncodigo.springboot.form.app.services.RoleService;

@Component
public class RolesPropertyEditor extends PropertyEditorSupport{
	
	@Autowired
	private RoleService service;

	@Override
	public void setAsText(String id) throws IllegalArgumentException {

		try {
			
			this.setValue(service.obtenerPorId(Integer.parseInt(id)));
			
		} catch (NumberFormatException e) {
			
			this.setValue(null);
		
		}

	}
	
	

}
