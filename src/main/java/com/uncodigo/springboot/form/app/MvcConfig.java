package com.uncodigo.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uncodigo.springboot.form.app.interceptors.TiempoTranscurridoInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	private TiempoTranscurridoInterceptor tiempoTranscurridoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**", "");
		
	}

}
