package com.uncodigo.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		setValue(text.toUpperCase().trim());
		
	}
	
	

}
